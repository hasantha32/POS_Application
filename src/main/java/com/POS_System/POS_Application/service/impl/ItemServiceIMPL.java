package com.POS_System.POS_Application.service.impl;

import com.POS_System.POS_Application.dao.ItemDAO;
import com.POS_System.POS_Application.dto.CustomerDTO;
import com.POS_System.POS_Application.dto.paginated.PaginatedResponseItemDTO;
import com.POS_System.POS_Application.dto.request.ItemSaveRequestDTO;
import com.POS_System.POS_Application.dto.response.ItemGetResponseDTO;
import com.POS_System.POS_Application.entity.Customer;
import com.POS_System.POS_Application.entity.Item;
import com.POS_System.POS_Application.entity.enums.MeasuringUnitType;
import com.POS_System.POS_Application.exception.BadRequestException;
import com.POS_System.POS_Application.exception.NotFoundException;
import com.POS_System.POS_Application.service.ItemService;
import com.POS_System.POS_Application.util.mappers.ItemMapper;
import org.hibernate.DuplicateMappingException;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;



    @Override
    public String saveItem(ItemSaveRequestDTO ItemSaveRequestDTO) {


//        Item item =new Item(
//                1,
//                ItemSaveRequestDTO.getItemName(),
//                ItemSaveRequestDTO.getMeasuringUnitType(),
//                ItemSaveRequestDTO.getBalanceQty(),
//                ItemSaveRequestDTO.getSupplierPrice(),
//                ItemSaveRequestDTO.getSellingPrice(),
//                true
//        );
//        itemDAO.save(item);
//        return item.getItemName()+"Successfully Saved";


        //---ItemSaveRequestDTO type ----------convert------------>Item type [[with mapstruct]]
        Item item=itemMapper.itemSaveRequestDtoToItem(ItemSaveRequestDTO);

        //---ItemSaveRequestDTO type ----------convert------------>Item type [[with model mapper]]
    //    Item item=modelMapper.map(
        //    ,Item.class);

        if(item.getMeasuringUnitType().equals("KILO_GRAM")|| item.getMeasuringUnitType().equals("LITER")|| item.getMeasuringUnitType().equals("GRAM")
                || item.getMeasuringUnitType().equals("MILLI_LITER") || item.getMeasuringUnitType().equals("NUMBER") ){
            throw new NotFoundException("Units not match");
        }
        if (!itemDAO.existsById(item.getItemId())){
            itemDAO.save(item);
            return item.getItemId()+" saved successfully";
        }
        else{
            throw new DuplicateKeyException("Already Added");
        }
    }




    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {

        boolean b=true;
//1st method
      //  List<Item> items=itemDAO.hghsgagdad(itemName,true);
//2nd method
        List<Item> items=itemDAO.findAllByItemNameEqualsAndActiveStateEquals(itemName,b);

//        List<ItemGetResponseDTO> itemGetResponseDTOList= new ArrayList<>();
//        if (items.size()>0){
//            for (Item item:items) {
//                ItemGetResponseDTO itemGetResponseDTO=new ItemGetResponseDTO(
//                        item.getItemId(),
//                        item.getItemName(),
//                        item.getBalanceQty(),
//                        item.getSupplierPrice(),
//                        item.getSellingPrice(),
//                        item.isActiveState()
//                );
//                itemGetResponseDTOList.add(itemGetResponseDTO);
//            }
//            return itemGetResponseDTOList;
//        }else {
//            throw new RuntimeException("Item is not active!");
//        }

        if (items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS=modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){
            }.getType());
            return itemGetResponseDTOS;
        }else {
            throw new RuntimeException("Item is not active!");
        }




    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName) {

        boolean b=true;

        List<Item> items=itemDAO.findAllByItemNameEqualsAndActiveStateEquals(itemName,b);
        if (items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS=itemMapper.entityListToDtoList(items);

            return itemGetResponseDTOS;
        }else {
            throw new RuntimeException("Item is not active!");
        }
    }


//    @Override
//    public List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus) {
//        List<Item> items=itemDAO.findAllByActiveStateEquals(activeStatus);
//        if (items.size()>0){
//            List<ItemGetResponseDTO> itemGetResponseDTOS=itemMapper.entityListToDtoList(items);
//
//            return itemGetResponseDTOS;
//        }else {
//            throw new NotFoundException("Item is not active!");
//        }
//    }

    //PEGINATION EXERCISE
    @Override
    public PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items=itemDAO.findAllByActiveStateEquals(activeStatus, PageRequest.of(page,size));

       //// int count =itemDAO.countAllByActiveStateEquals(activeStatus);

        if (items.getSize()<1){
            throw new NotFoundException("No Data");
        }
        PaginatedResponseItemDTO paginatedResponseItemDTO=new PaginatedResponseItemDTO(
                itemMapper.ListDTOToPage(items) ,

               // 2
                ////count
                itemDAO.countAllByActiveStateEquals(activeStatus)
        );
        return paginatedResponseItemDTO;
    }
}

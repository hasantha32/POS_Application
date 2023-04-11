package com.POS_System.POS_Application.util.mappers;

import com.POS_System.POS_Application.dto.request.ItemSaveRequestDTO;
import com.POS_System.POS_Application.dto.response.ItemGetResponseDTO;
import com.POS_System.POS_Application.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    //saveItem method in controller class
    //ItemSaveRequestDTO  ---------------------->Item
    Item itemSaveRequestDtoToItem(ItemSaveRequestDTO ItemSaveRequestDTO);

    //getItemByNameAndStatusBymapstruct method in controller class
    //ItemList<> --------------> ItemResponseDTO
    List<ItemGetResponseDTO> entityListToDtoList(List<Item> items);

    //pagination
    //getItemByActiveStatusWithPaginated method in controller class
    //Page<Item> items------------>List<ItemGetResponseDTO> list
    List<ItemGetResponseDTO> ListDTOToPage(Page<Item> items);

}


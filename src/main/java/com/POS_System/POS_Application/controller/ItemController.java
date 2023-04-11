package com.POS_System.POS_Application.controller;

import com.POS_System.POS_Application.dto.paginated.PaginatedResponseItemDTO;
import com.POS_System.POS_Application.dto.request.ItemSaveRequestDTO;
import com.POS_System.POS_Application.dto.response.ItemGetResponseDTO;
import com.POS_System.POS_Application.service.ItemService;
import com.POS_System.POS_Application.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("/itemcontroller")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;
    //######################################################################################//
////####################$$$$$$$$$$$    SaveItem with normal method (String return type)   $$$$$#######################

//                @PostMapping("/save")
//                public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
//                    String message= itemService.saveItem(itemSaveRequestDTO);
//                    return message;
//                }

    //######################################################################################//
////##############$$$$$$$$$$    SaveItem with Response Entity method (ResponseEntity return type)   $$$$$##################//
    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        String message= itemService.saveItem(itemSaveRequestDTO);
      //  return message;

//        ResponseEntity<StandardResponse> response=new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"Success",message), HttpStatus.CREATED
//        );
//        return response;
//
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
     path="/get-by-name",
     params = "name")
    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value ="name") String itemName){
        List<ItemGetResponseDTO> itemGetResponseDTOS=itemService.getItemByNameAndStatus(itemName);
        return itemGetResponseDTOS;
    }


    @GetMapping(
            path="/get-by-name-with-mapstruct",
            params = "name")
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(@RequestParam(value ="name") String itemName){
        List<ItemGetResponseDTO> itemGetResponseDTOS=itemService.getItemByNameAndStatusBymapstruct(itemName);
        return itemGetResponseDTOS;
    }

//pegination
    @GetMapping(
            path="/get-all-item-by-status",
            params = {"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getItemByActiveStatusWithPaginated(
            @RequestParam(value ="activeStatus") boolean activeStatus,
            @RequestParam(value ="page") int page,
            @RequestParam(value ="size") @Max(50) int size
            ){
    //    List<ItemGetResponseDTO> itemGetResponseDTOS=itemService.getItemByActiveStatusWithPaginated(activeStatus,page,size);

        PaginatedResponseItemDTO paginatedResponseItemDTO=itemService.getItemByActiveStatusWithPaginated(activeStatus,page,size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }










}

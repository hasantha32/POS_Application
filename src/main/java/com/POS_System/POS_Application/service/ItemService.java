package com.POS_System.POS_Application.service;

import com.POS_System.POS_Application.dto.paginated.PaginatedResponseItemDTO;
import com.POS_System.POS_Application.dto.request.ItemSaveRequestDTO;
import com.POS_System.POS_Application.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName);

  //  List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus);

    PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size);
}

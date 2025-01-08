package com.example.selectservice.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetMenuListResponseDTO {
    List<GetCompleteProductsListResponseDTO> getCompleteProductsList;
    List<GetSideMenuListResponseDTO> getSideMenuList;
}

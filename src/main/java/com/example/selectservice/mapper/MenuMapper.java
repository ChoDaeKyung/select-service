package com.example.selectservice.mapper;

import com.example.selectservice.dto.GetDetailProductsDTO;
import com.example.selectservice.dto.GetCompleteProductsListResponseDTO;
import com.example.selectservice.dto.GetSideMenuListResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<GetCompleteProductsListResponseDTO> getMenuList();
    List<GetSideMenuListResponseDTO> getSideMenuList();
    List<GetSideMenuListResponseDTO> getSideMenuByCategory(String category);
    List<GetDetailProductsDTO> getProductsByCompleteProduct(String name);
    GetCompleteProductsListResponseDTO getMenuListByName(String name);
}

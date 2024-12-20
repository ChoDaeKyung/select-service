package com.example.selectservice.mapper;

import com.example.selectservice.dto.GetDetailProductsDTO;
import com.example.selectservice.dto.GetMenuListResponseDTO;
import com.example.selectservice.model.Products;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<GetMenuListResponseDTO> getMenuList();
    List<GetDetailProductsDTO> getProductsByCompleteProduct(String name);
}

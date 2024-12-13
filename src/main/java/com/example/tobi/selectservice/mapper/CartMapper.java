package com.example.tobi.selectservice.mapper;

import com.example.tobi.selectservice.dto.CartProductRequestDTO;
import com.example.tobi.selectservice.dto.InsertCartRequestDTO;
import com.example.tobi.selectservice.dto.ProductRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    void insertProduct(List<CartProductRequestDTO> cartProductRequestDTOSList);
    void insertCompleteProduct(String name, int totalPrice, String buyer, String productId);
    int selectIdByproductId(String productId);
}

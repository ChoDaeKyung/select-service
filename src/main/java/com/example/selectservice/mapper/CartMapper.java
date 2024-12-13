package com.example.selectservice.mapper;

import com.example.selectservice.dto.CartProductRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    void insertProduct(List<CartProductRequestDTO> cartProductRequestDTOSList);
    void insertCompleteProduct(String name, int totalPrice, String buyer, String productId);
    int selectIdByproductId(String productId);
}

package com.example.selectservice.mapper;

import com.example.selectservice.dto.CartProductRequestDTO;
import com.example.selectservice.dto.CartProductResponseDTO;
import com.example.selectservice.dto.CompleteCartResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    void insertProduct(List<CartProductRequestDTO> cartProductRequestDTOSList);
    void insertCompleteProduct(String name, int totalPrice, String buyer, String productId);
    int selectIdByproductId(String productId);
    List<CompleteCartResponseDTO> getCompleteCartList(@Param("nickName") String nickName);
    List<CartProductResponseDTO> getCartProductList(@Param("nickName") String nickName);
}

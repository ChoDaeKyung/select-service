package com.example.selectservice.service;

import com.example.selectservice.dto.CartProductRequestDTO;
import com.example.selectservice.dto.InsertCartRequestDTO;
import com.example.selectservice.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    public void insertCart(InsertCartRequestDTO insertCartRequestDTO) {
        List<CartProductRequestDTO> allProducts = insertCartRequestDTO.getProductsList();
        String name = insertCartRequestDTO.getName();
        int totalPrice = insertCartRequestDTO.getTotalPrice();
        String buyer = insertCartRequestDTO.getBuyer();
        String productId = insertCartRequestDTO.getProductId();


        cartMapper.insertCompleteProduct(name, totalPrice, buyer, productId);
        int id = cartMapper.selectIdByproductId(productId);
        for (CartProductRequestDTO product : allProducts) {
            product.setId(id);
        }
        cartMapper.insertProduct(allProducts);
    }

}

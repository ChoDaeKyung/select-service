package com.example.tobi.selectservice.service;

import com.example.tobi.selectservice.client.FileClient;
import com.example.tobi.selectservice.dto.CartProductRequestDTO;
import com.example.tobi.selectservice.dto.InsertCartRequestDTO;
import com.example.tobi.selectservice.dto.ProductRequestDTO;
import com.example.tobi.selectservice.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

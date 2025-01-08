package com.example.selectservice.service;

import com.example.selectservice.dto.*;
import com.example.selectservice.mapper.CartMapper;
import com.example.selectservice.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;
    private final MenuMapper menuMapper;

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

    public void insertCompleteProductToCart(CompleteCartRequestDTO completeCartRequestDTO) {
        String name = completeCartRequestDTO.getName();
        int totalPrice = completeCartRequestDTO.getPrice();
        String buyer = completeCartRequestDTO.getBuyer();
        String productId = completeCartRequestDTO.getProductId();

        cartMapper.insertCompleteProduct(name, totalPrice, buyer, productId);
        int id = cartMapper.selectIdByproductId(productId);
        List<GetDetailProductsDTO> productsByCompleteProduct = menuMapper.getProductsByCompleteProduct(name);

        List<CartProductRequestDTO> cartProducts = productsByCompleteProduct.stream()
                .map(product -> CartProductRequestDTO.builder()
                        .id(id) // 조회된 CompleteProduct의 ID로 설정
                        .name(product.getName())
                        .category(product.getCategory())
                        .price(product.getPrice())
                        .buyer(buyer) // CompleteCartRequestDTO에서 가져온 구매자 정보로 설정
                        .build())
                .toList();

        // 5. 변환된 CartProductRequestDTO 리스트를 DB에 삽입
        cartMapper.insertProduct(cartProducts);
    }

    public void insertSideMenuToCart(CompleteCartRequestDTO completeCartRequestDTO) {
        String name = completeCartRequestDTO.getName();
        int totalPrice = completeCartRequestDTO.getPrice();
        String buyer = completeCartRequestDTO.getBuyer();
        String productId = completeCartRequestDTO.getProductId();

        cartMapper.insertCompleteProduct(name, totalPrice, buyer, productId);
    }

    public CartResponseDTO getCartList(String nickName) {

        List<CompleteCartResponseDTO> completeCartList = cartMapper.getCompleteCartList(nickName);
        List<CartProductResponseDTO> cartProductList = cartMapper.getCartProductList(nickName);

        return CartResponseDTO.builder()
                .CompleteCartList(completeCartList)
                .CartProductList(cartProductList)
                .build();
    }

    public void insertCustomToCart(CustomCartRequestDTO customCartRequestDTO) {
        CompleteCartRequestDTO completeCart = customCartRequestDTO.getCompleteCartRequestDTO();
        String name = completeCart.getName();
        int totalPrice = completeCart.getPrice();
        String buyer = completeCart.getBuyer();
        String productId = completeCart.getProductId();


        // CompleteCartRequestDTO를 DB에 삽입
        cartMapper.insertCompleteProduct(name, totalPrice, buyer, productId);

        int id = cartMapper.selectIdByproductId(productId);

        // 2. CustomProductsRequestDTO 리스트 처리
        List<CustomProductsRequestDTO> customProducts = customCartRequestDTO.getCustomProductsRequestDTO();
        if (customProducts != null && !customProducts.isEmpty()) {
            // 각 CustomProductsRequestDTO에 id를 설정
            customProducts.forEach(product -> product.setId(id));

            // CustomProductsRequestDTO 리스트를 DB에 삽입
            cartMapper.insertCustomProducts(customProducts);
        }
    }
}


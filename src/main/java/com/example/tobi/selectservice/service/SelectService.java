package com.example.tobi.selectservice.service;

import com.example.tobi.selectservice.client.FileClient;
import com.example.tobi.selectservice.dto.GetSelectProductResponseDTO;
import com.example.tobi.selectservice.dto.ProductRequestDTO;
import com.example.tobi.selectservice.dto.SelectRequestDTO;
import com.example.tobi.selectservice.mapper.SelectMapper;
import com.example.tobi.selectservice.model.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SelectService {

    private final SelectMapper selectMapper;
    private final FileClient fileClient;

    public void insertSelect(SelectRequestDTO selectRequestDTO) {
        // SelectRequestDTO 안의 모든 ProductRequestDTO를 추출
        List<ProductRequestDTO> allProducts = new ArrayList<>();
        selectRequestDTO.getProductsList().forEach(allProducts::addAll); // 모든 카테고리의 상품 리스트를 합침
        String completeProduct = selectRequestDTO.getCompleteProduct();
        int totalPrice = selectRequestDTO.getTotalPrice();
        String orderId = selectRequestDTO.getOrderId();

        selectMapper.insertCompleteProduct(completeProduct, totalPrice, orderId);

        int id = selectMapper.selectOrderIdById(orderId);


        allProducts.forEach(product -> product.setOrderNumber(id));

        // Products 저장
        selectMapper.insertSelect(allProducts);
    }

    public List<GetSelectProductResponseDTO> getSelectProducts() {
        List<Products> products = selectMapper.getSelectProducts();

        List<String> imagePaths = products.stream()
                .map(Products::getImagePath) // Products 객체에서 imagePath 필드만 추출
                .collect(Collectors.toList()); // 추출한 값들을 List로 수집

        System.out.println("imagePaths: " + imagePaths);

        List<String> imageList = fileClient.getImage(imagePaths);


        // Products와 인코딩된 이미지를 매칭하여 DTO 리스트를 생성
        List<GetSelectProductResponseDTO> responseDTOs = IntStream.range(0, products.size())
                .mapToObj(i -> GetSelectProductResponseDTO.builder()
                        .name(products.get(i).getName())
                        .category(products.get(i).getCategory())
                        .price(products.get(i).getPrice())
                        .image(imageList.get(i)) // 매칭된 이미지를 DTO에 설정
                        .build())
                .collect(Collectors.toList());

        System.out.println("responseDTOs: " + responseDTOs);

        return responseDTOs; // 최종 DTO 리스트 반환

    }
}

package com.example.selectservice.service;

import com.example.selectservice.client.FileClient;
import com.example.selectservice.dto.GetDetailProductsDTO;
import com.example.selectservice.dto.GetMenuListResponseDTO;
import com.example.selectservice.dto.GetSelectProductResponseDTO;
import com.example.selectservice.mapper.MenuMapper;
import com.example.selectservice.model.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuMapper menuMapper;
    private final FileClient fileClient;

    public List<GetMenuListResponseDTO> getMenuList() {
        List<GetMenuListResponseDTO> products = menuMapper.getMenuList();

        List<String> imagePaths = products.stream()
                .map(GetMenuListResponseDTO::getImage) // Products 객체에서 imagePath 필드만 추출
                .collect(Collectors.toList()); // 추출한 값들을 List로 수집

        System.out.println("imagePaths: " + imagePaths);

        List<String> imageList = fileClient.getImage(imagePaths);


        // Products와 인코딩된 이미지를 매칭하여 DTO 리스트를 생성
        List<GetMenuListResponseDTO> responseDTOs = IntStream.range(0, products.size())
                .mapToObj(i -> GetMenuListResponseDTO.builder()
                        .name(products.get(i).getName())
                        .price(products.get(i).getPrice())
                        .detail(products.get(i).getDetail())
                        .image(imageList.get(i)) // 매칭된 이미지를 DTO에 설정
                        .build())
                .collect(Collectors.toList());

        System.out.println("responseDTOs: " + responseDTOs);

        return responseDTOs; // 최종 DTO 리스트 반환

    }

    public List<GetDetailProductsDTO> getProductsByCompleteProduct (String name) {
        List<GetDetailProductsDTO> products = menuMapper.getProductsByCompleteProduct(name);

        List<String> imagePaths = products.stream()
                .map(GetDetailProductsDTO::getImage) // Products 객체에서 imagePath 필드만 추출
                .collect(Collectors.toList()); // 추출한 값들을 List로 수집

        System.out.println("imagePaths: " + imagePaths);

        List<String> imageList = fileClient.getImage(imagePaths);


        // Products와 인코딩된 이미지를 매칭하여 DTO 리스트를 생성
        List<GetDetailProductsDTO> responseDTOs = IntStream.range(0, products.size())
                .mapToObj(i -> GetDetailProductsDTO.builder()
                        .name(products.get(i).getName())
                        .category(products.get(i).getCategory())
                        .image(imageList.get(i)) // 매칭된 이미지를 DTO에 설정
                        .build())
                .collect(Collectors.toList());

        System.out.println("responseDTOs: " + responseDTOs);

        return responseDTOs; // 최종 DTO 리스트 반환
    }

}

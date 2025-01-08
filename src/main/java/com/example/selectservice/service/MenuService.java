package com.example.selectservice.service;

import com.example.selectservice.client.FileClient;
import com.example.selectservice.dto.GetDetailProductsDTO;
import com.example.selectservice.dto.GetCompleteProductsListResponseDTO;
import com.example.selectservice.dto.GetMenuListResponseDTO;
import com.example.selectservice.dto.GetSideMenuListResponseDTO;
import com.example.selectservice.mapper.MenuMapper;
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

    public GetMenuListResponseDTO getMenuList() {
        List<GetCompleteProductsListResponseDTO> completeProducts = menuMapper.getMenuList();
        List<GetSideMenuListResponseDTO> sideMenu = menuMapper.getSideMenuList();

        List<String> imagePaths = completeProducts.stream()
                .map(GetCompleteProductsListResponseDTO::getImage) // Products 객체에서 imagePath 필드만 추출
                .collect(Collectors.toList()); // 추출한 값들을 List로 수집

        System.out.println("imagePaths: " + imagePaths);

        List<String> imageList = fileClient.getImage(imagePaths);

        List<String> imagePaths2 = sideMenu.stream()
                .map(GetSideMenuListResponseDTO::getImage) // Products 객체에서 imagePath 필드만 추출
                .collect(Collectors.toList()); // 추출한 값들을 List로 수집

        System.out.println("imagePaths2: " + imagePaths2);

        List<String> imageList2 = fileClient.getImage(imagePaths2);

        // Products와 인코딩된 이미지를 매칭하여 DTO 리스트를 생성
        List<GetCompleteProductsListResponseDTO> getCompleteProductsListResponseDTOS = IntStream.range(0, completeProducts.size())
                .mapToObj(i -> GetCompleteProductsListResponseDTO.builder()
                        .name(completeProducts.get(i).getName())
                        .price(completeProducts.get(i).getPrice())
                        .detail(completeProducts.get(i).getDetail())
                        .image(imageList.get(i)) // 매칭된 이미지를 DTO에 설정
                        .build())
                .collect(Collectors.toList());

        System.out.println("getCompleteProductsListResponseDTOS: " + getCompleteProductsListResponseDTOS);

        List<GetSideMenuListResponseDTO> getSideMenuListResponseDTOS = IntStream.range(0, sideMenu.size())
                .mapToObj(i -> GetSideMenuListResponseDTO.builder()
                        .name(sideMenu.get(i).getName())
                        .category(sideMenu.get(i).getCategory())
                        .price(sideMenu.get(i).getPrice())
                        .detail(sideMenu.get(i).getDetail())
                        .image(imageList2.get(i)) // 매칭된 이미지를 DTO에 설정
                        .build())
                .collect(Collectors.toList());


        return GetMenuListResponseDTO.builder()
                .getCompleteProductsList(getCompleteProductsListResponseDTOS) // 업데이트된 Complete Products 리스트
                .getSideMenuList(getSideMenuListResponseDTOS) // Side Menu 리스트 추가
                .build();

    }

    public List<GetSideMenuListResponseDTO> getSideMenuByCategory(String category) {
        List<GetSideMenuListResponseDTO> sideMenu = menuMapper.getSideMenuByCategory(category);

        List<String> imagePaths = sideMenu.stream()
                .map(GetSideMenuListResponseDTO::getImage) // Products 객체에서 imagePath 필드만 추출
                .collect(Collectors.toList()); // 추출한 값들을 List로 수집

        System.out.println("imagePaths: " + imagePaths);

        List<String> imageList = fileClient.getImage(imagePaths);

        List<GetSideMenuListResponseDTO> getSideMenuListResponseDTOS = IntStream.range(0, sideMenu.size())
                .mapToObj(i -> GetSideMenuListResponseDTO.builder()
                        .name(sideMenu.get(i).getName())
                        .category(sideMenu.get(i).getCategory())
                        .price(sideMenu.get(i).getPrice())
                        .detail(sideMenu.get(i).getDetail())
                        .image(imageList.get(i)) // 매칭된 이미지를 DTO에 설정
                        .build())
                .collect(Collectors.toList());


        return getSideMenuListResponseDTOS;
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

    public GetCompleteProductsListResponseDTO getMenuListByName (String name) {
        return menuMapper.getMenuListByName(name);
    }
}

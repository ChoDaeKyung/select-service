package com.example.selectservice.controller;

import com.example.selectservice.dto.AddCompleteProductDetailDto;
import com.example.selectservice.dto.AddCompleteProductRequestDTO;
import com.example.selectservice.dto.AddProductRequestDTO;
import com.example.selectservice.dto.AddSideMenuRequestDTO;
import com.example.selectservice.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping(value = "/addCompleteProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addCompleteProduct(
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("detail") String detail,
            @RequestParam("productList") String productList,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        System.out.println("name = " + name);
        System.out.println("price = " + price);
        System.out.println("detail = " + detail);
        System.out.println("productList = " + productList);
        System.out.println("image = " + image);

        List<AddCompleteProductDetailDto> productsLists = new ObjectMapper().readValue(productList, new TypeReference<List<AddCompleteProductDetailDto>>() {});

        System.out.println("productsLists :: " + productsLists);

        AddCompleteProductRequestDTO build = AddCompleteProductRequestDTO.builder()
                .completeName(name).price(price).detail(detail).productsList(productsLists).build();

        adminService.addCompleteProduct(build, image);
        return "상품 등록 성공!";
    }

    @PostMapping(value = "/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addProduct(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price") int price,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        System.out.println("name = " + name);
        System.out.println("category = " + category);
        System.out.println("price = " + price);
        System.out.println("image = " + image);

        AddProductRequestDTO build = AddProductRequestDTO.builder()
                .name(name).category(category).price(price).build();

        adminService.addProduct(build, image);

        return "상품 등록 성공!";
    }

    @PostMapping(value = "/addSideMenu", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addSideMenu(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price") int price,
            @RequestParam("detail") String detail,
            @RequestPart("image") MultipartFile image
            ) throws IOException {
        System.out.println("name = " + name);
        System.out.println("category = " + category);
        System.out.println("price = " + price);
        System.out.println("detail = " + detail);
        System.out.println("image = " + image);

        AddSideMenuRequestDTO build = AddSideMenuRequestDTO.builder()
                .name(name).category(category).price(price).detail(detail).build();

        adminService.addSideMenu(build, image);
        return "상품 등록 성공!";
    }

}
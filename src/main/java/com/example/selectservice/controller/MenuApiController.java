package com.example.selectservice.controller;

import com.example.selectservice.dto.GetDetailProductsDTO;
import com.example.selectservice.dto.GetCompleteProductsListResponseDTO;
import com.example.selectservice.dto.GetMenuListResponseDTO;
import com.example.selectservice.dto.GetSideMenuListResponseDTO;
import com.example.selectservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuApiController {

    private final MenuService menuService;

    @GetMapping
    public GetMenuListResponseDTO getMenuList(){
        GetMenuListResponseDTO getMenuList = menuService.getMenuList();
        System.out.println("getMenuList : " + getMenuList);
        return getMenuList;
    }

    @GetMapping("/getProducts")
    public List<GetDetailProductsDTO> getProductsByCompleteProduct(@RequestParam String name){
        List<GetDetailProductsDTO> getProducts = menuService.getProductsByCompleteProduct(name);
        System.out.println("getProducts : " + getProducts);
        return getProducts;
    }

    @GetMapping("/getMenuListByName")
    public GetCompleteProductsListResponseDTO getMenuListByName(@RequestParam String name){
        return null;
    }

    @GetMapping("/getSideMenuByCategory")
    public List<GetSideMenuListResponseDTO> getSideMenuByCategory(@RequestParam String category){
        List<GetSideMenuListResponseDTO> getSideMenuByCategory = menuService.getSideMenuByCategory(category);
        return getSideMenuByCategory;
    }

}

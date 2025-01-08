package com.example.selectservice.controller;

import com.example.selectservice.dto.AddCompleteProductRequestDTO;
import com.example.selectservice.dto.AddProductRequestDTO;
import com.example.selectservice.dto.AddSideMenuRequestDTO;
import com.example.selectservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping("/addCompleteProduct")
    public String addCompleteProduct(
            @RequestBody AddCompleteProductRequestDTO addCompleteProductRequestDTO
    ){
        System.out.println("addCompleteProductRequestDTO :: " + addCompleteProductRequestDTO);
        adminService.addCompleteProduct(addCompleteProductRequestDTO);
        return "상품 등록 성공!";
    }

    @PostMapping("/addProduct")
    public String addProduct(
            @RequestBody AddProductRequestDTO addProductRequestDTO
    ){
        System.out.println("addProductRequestDTO :: " + addProductRequestDTO);
        adminService.addProduct(addProductRequestDTO);
        return "상품 등록 성공!";
    }

    @PostMapping("/addSideMenu")
    public String addSideMenu(
            @RequestBody AddSideMenuRequestDTO addSideMenuRequestDTO
            ){
        System.out.println("AddSideMenuRequestDTO :: " + addSideMenuRequestDTO);
        adminService.addSideMenu(addSideMenuRequestDTO);
        return "상품 등록 성공!";
    }

}
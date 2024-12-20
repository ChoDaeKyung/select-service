package com.example.selectservice.controller;

import com.example.selectservice.dto.AddCompleteProductRequestDTO;
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

    @PostMapping
    public String addCompleteProduct(
            @RequestBody AddCompleteProductRequestDTO addCompleteProductRequestDTO
    ){
        System.out.println("addCompleteProductRequestDTO :: " + addCompleteProductRequestDTO);
        adminService.addCompleteProduct(addCompleteProductRequestDTO);
        return "상품 등록 성공!";
    }
}
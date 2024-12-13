package com.example.tobi.selectservice.controller;

import com.example.tobi.selectservice.dto.InsertCartRequestDTO;
import com.example.tobi.selectservice.dto.SelectRequestDTO;
import com.example.tobi.selectservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;

    @PostMapping
    public String insertSelect(
            @RequestBody InsertCartRequestDTO insertCartRequestDTO
    ){
        System.out.println("insertCartRequestDTO :: " + insertCartRequestDTO);
        cartService.insertCart(insertCartRequestDTO);
        return "success";
    }
}

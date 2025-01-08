package com.example.selectservice.controller;

import com.example.selectservice.dto.CartResponseDTO;
import com.example.selectservice.dto.CompleteCartRequestDTO;
import com.example.selectservice.dto.CustomCartRequestDTO;
import com.example.selectservice.dto.InsertCartRequestDTO;
import com.example.selectservice.service.CartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/completeProduct")
    private String insertCompleteProductToCart(
            @RequestBody CompleteCartRequestDTO completeCartRequestDTO
            ){
        System.out.println("completeCartRequestDTO :: " + completeCartRequestDTO);
        cartService.insertCompleteProductToCart(completeCartRequestDTO);
        return "success";
    }

    @PostMapping("/sideMenu")
    private String insertSideMenuToCart(
            @RequestBody CompleteCartRequestDTO completeCartRequestDTO
    ){
        System.out.println("completeCartRequestDTO :: " + completeCartRequestDTO);
        cartService.insertSideMenuToCart(completeCartRequestDTO);
        return "success";
    }

    @GetMapping("/getCartList")
    private CartResponseDTO getCartList(@RequestParam String nickName){
        System.out.println("nickName :: " + nickName);
        return cartService.getCartList(nickName);
    }

    @PostMapping("/custom")
    private String insertCustomToCart(
            @RequestBody CustomCartRequestDTO customCartRequestDTO
            ){
        cartService.insertCustomToCart(customCartRequestDTO);
        return "success";
    }

}

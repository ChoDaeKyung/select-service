package com.example.selectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CartResponseDTO {
    private List<CompleteCartResponseDTO> CompleteCartList;
    private List<CartProductResponseDTO> CartProductList;
}

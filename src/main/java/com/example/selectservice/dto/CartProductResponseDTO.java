package com.example.selectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CartProductResponseDTO {
    private int id;
    private String name;
    private String category;
    private int price;
}

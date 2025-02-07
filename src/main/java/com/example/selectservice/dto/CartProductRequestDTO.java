package com.example.selectservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class CartProductRequestDTO {
    private int id;
    private String name;
    private String category;
    private int price;
    private String buyer;
}

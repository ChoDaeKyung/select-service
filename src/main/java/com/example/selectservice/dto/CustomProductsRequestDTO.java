package com.example.selectservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CustomProductsRequestDTO {
    private int id; // 추가된 필드
    private String name;
    private String category;
    private int price;
    private String buyer;
}

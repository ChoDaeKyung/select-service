package com.example.selectservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductRequestDTO {
    private int orderNumber;
    private String name;
    private String category;
    private int price;
    private String buyer;
}

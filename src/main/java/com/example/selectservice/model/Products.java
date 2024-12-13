package com.example.selectservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Products {
    private String name;
    private String category;
    private int price;
    private String imagePath;
}

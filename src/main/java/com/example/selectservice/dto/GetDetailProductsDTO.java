package com.example.selectservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
public class GetDetailProductsDTO {
    private String name;
    private String category;
    private int price;
    private String image;
}

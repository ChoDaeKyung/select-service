package com.example.selectservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AddCompleteProductDetailDto {
    private String name;
    private String category;
    private String price;
}

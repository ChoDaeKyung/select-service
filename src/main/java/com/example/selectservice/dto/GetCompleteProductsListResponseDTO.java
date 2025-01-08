package com.example.selectservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCompleteProductsListResponseDTO {
    private String name;
    private int price;
    private String detail;
    private String image;
}

package com.example.selectservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class AddCompleteProductRequestDTO {
    private String completeName;
    private List<AddCompleteProductDetailDto> productsList;
    private int price;
    private String detail;
    private String imagePath;
}

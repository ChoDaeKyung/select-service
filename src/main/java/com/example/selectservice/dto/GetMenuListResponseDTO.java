package com.example.selectservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
public class GetMenuListResponseDTO {
    private String name;
    private int price;
    private String detail;
    private String image;
}

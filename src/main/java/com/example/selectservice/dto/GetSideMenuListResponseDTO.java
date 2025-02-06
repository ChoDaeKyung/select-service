package com.example.selectservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GetSideMenuListResponseDTO {
    private String name;
    private String category;
    private int price;
    private String detail;
    private String image;
}

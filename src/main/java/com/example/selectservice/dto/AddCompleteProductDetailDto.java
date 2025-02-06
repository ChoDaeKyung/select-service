package com.example.selectservice.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddCompleteProductDetailDto {
    private String name;
    private String category;
    private String price;
}

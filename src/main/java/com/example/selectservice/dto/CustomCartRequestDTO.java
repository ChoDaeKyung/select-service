package com.example.selectservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class CustomCartRequestDTO {
    private List<CustomProductsRequestDTO> customProductsRequestDTO;
    private CompleteCartRequestDTO completeCartRequestDTO;
}

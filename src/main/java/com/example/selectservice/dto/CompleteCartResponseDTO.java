package com.example.selectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompleteCartResponseDTO {
    private int id;
    private String name;
    private int totalPrice;
}

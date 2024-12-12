package com.example.tobi.selectservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SelectRequestDTO {
    private List<List<ProductRequestDTO>> productsList;
    private String completeProduct;
    private int totalPrice;
    private String orderId;
}
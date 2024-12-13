package com.example.selectservice.mapper;

import com.example.selectservice.dto.ProductRequestDTO;
import com.example.selectservice.model.Products;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SelectMapper {
    void insertSelect(List<ProductRequestDTO> productRequestDTOList);
    void insertCompleteProduct(String completeProduct, int totalPrice, String orderId);
    int selectIdByOrderId(String orderId);
    List<Products> getSelectProducts();
}

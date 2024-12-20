package com.example.selectservice.service;

import com.example.selectservice.dto.AddCompleteProductDetailDto;
import com.example.selectservice.dto.AddCompleteProductRequestDTO;
import com.example.selectservice.dto.ProductRequestDTO;
import com.example.selectservice.dto.UsedUpdateRequestDTO;
import com.example.selectservice.mapper.AdminMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    public void addCompleteProduct(AddCompleteProductRequestDTO requestDTO) {

        adminMapper.addCompleteProduct(requestDTO);

        // Add completeName to the 'used' field for each product in the list
        for (AddCompleteProductDetailDto product : requestDTO.getProductsList()) {
            UsedUpdateRequestDTO usedUpdateRequestDTO = new UsedUpdateRequestDTO();
            usedUpdateRequestDTO.setName(product.getName());
            usedUpdateRequestDTO.setCompleteName(requestDTO.getCompleteName());

            // Call the mapper method to update the 'used' column in the database
            adminMapper.addUsed(usedUpdateRequestDTO);
        }
    }

}

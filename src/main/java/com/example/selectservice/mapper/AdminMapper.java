package com.example.selectservice.mapper;

import com.example.selectservice.dto.AddCompleteProductRequestDTO;
import com.example.selectservice.dto.AddProductRequestDTO;
import com.example.selectservice.dto.AddSideMenuRequestDTO;
import com.example.selectservice.dto.UsedUpdateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    void addCompleteProduct(AddCompleteProductRequestDTO addCompleteProductRequestDTO);
    void addProduct(AddProductRequestDTO addProductRequestDTO);
    void addSideMenu(AddSideMenuRequestDTO addSideMenuRequestDTO);
    void addUsed(UsedUpdateRequestDTO usedUpdateRequestDTO);
}

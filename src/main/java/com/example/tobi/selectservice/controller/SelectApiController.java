package com.example.tobi.selectservice.controller;

import com.example.tobi.selectservice.dto.GetSelectProductResponseDTO;
import com.example.tobi.selectservice.dto.SelectRequestDTO;
import com.example.tobi.selectservice.service.SelectService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/select")
@RequiredArgsConstructor
public class SelectApiController {

    private final SelectService selectService;

    @PostMapping
    public String insertSelect(
            @RequestBody SelectRequestDTO selectRequestDTO
            ){
        System.out.println("selectRequestDTO :: " + selectRequestDTO);
        selectService.insertSelect(selectRequestDTO);
        return "success";
    }

    @GetMapping
    public List<GetSelectProductResponseDTO> getSelectProduct(){
        List<GetSelectProductResponseDTO> selectProductsList = selectService.getSelectProducts();
        return selectProductsList;
    }

}
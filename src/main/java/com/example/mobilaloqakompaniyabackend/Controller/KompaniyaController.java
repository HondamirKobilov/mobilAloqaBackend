package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.KompaniyaDto;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kompaniyaApi")
public class KompaniyaController {
    @Autowired
    LoyihalashService loyihalashService;

    @PostMapping("/kompaniyaAdd")
    public HttpEntity<?> BankPost(@RequestBody KompaniyaDto kompaniyaDto){
         ApiResponse apiResponse =loyihalashService.addKompaniya(kompaniyaDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
}

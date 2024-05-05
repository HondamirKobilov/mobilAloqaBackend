package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.TariflarDto;
import com.example.mobilaloqakompaniyabackend.DTO.XizmatlarDto;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xizmatlarApi")
public class XizmatlarController {
    @Autowired
    LoyihalashService loyihalashService;

    @PostMapping("/xizmatlarAdd")
    public HttpEntity<?> BankPost(@RequestBody XizmatlarDto xizmatlarDto){
        ApiResponse apiResponse = loyihalashService.addXizmatlar(xizmatlarDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
}

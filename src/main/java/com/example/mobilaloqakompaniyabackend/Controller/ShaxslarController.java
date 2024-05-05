package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.KompaniyaDto;
import com.example.mobilaloqakompaniyabackend.DTO.ShaxslarDto;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/shaxlarApi")
public class ShaxslarController {
    @Autowired
    LoyihalashService loyihalashService;

    @PostMapping("/shaxslarAdd")
    public HttpEntity<?> BankPost(@RequestBody ShaxslarDto shaxslarDto){
        ApiResponse apiResponse =loyihalashService.addshaxslar(shaxslarDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
}

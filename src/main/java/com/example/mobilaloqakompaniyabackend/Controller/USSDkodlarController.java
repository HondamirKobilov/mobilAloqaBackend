package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.TulovDto;
import com.example.mobilaloqakompaniyabackend.DTO.USSDkodlarDto;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ussdApi")
public class USSDkodlarController {
    @Autowired
    LoyihalashService loyihalashService;

    @PostMapping("/ussdAdd")
    public HttpEntity<?> BankPost(@RequestBody USSDkodlarDto ussDkodlarDto){
        ApiResponse apiResponse =loyihalashService.addUSSDkodlar(ussDkodlarDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
}

package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.KompaniyaDto;
import com.example.mobilaloqakompaniyabackend.DTO.SimKartakodDto;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simkartaKodApi")
public class SimkartaKodController {
    @Autowired
    LoyihalashService loyihalashService;

    @PostMapping("/simkartaKodAdd")
    public HttpEntity<?> BankPost(@RequestBody SimKartakodDto simKartakodDto){
        ApiResponse apiResponse =loyihalashService.addSimkartaKod(simKartakodDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }


}

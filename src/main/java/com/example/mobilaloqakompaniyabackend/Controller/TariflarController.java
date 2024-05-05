package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.PaketlarDto;
import com.example.mobilaloqakompaniyabackend.DTO.TariflarDto;
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
@RequestMapping("/tariflarApi")
public class TariflarController {
    @Autowired
    LoyihalashService loyihalashService;

    @PostMapping("/tariflarAdd")
    public HttpEntity<?> BankPost(@RequestBody TariflarDto tariflarDto){
        ApiResponse apiResponse =loyihalashService.addTariflar(tariflarDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
}

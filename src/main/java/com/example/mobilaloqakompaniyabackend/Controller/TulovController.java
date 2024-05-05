package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.TariflarDto;
import com.example.mobilaloqakompaniyabackend.DTO.TulovDto;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tulovApi")
public class TulovController {
    @Autowired
    LoyihalashService loyihalashService;

    @PostMapping("/simkartaAdd/{simkartaId}")
    public HttpEntity<?> BankPost(@RequestBody TulovDto tulovDto, @PathVariable Integer simkartaId){
        ApiResponse apiResponse =loyihalashService.addTulov(tulovDto,simkartaId);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
}

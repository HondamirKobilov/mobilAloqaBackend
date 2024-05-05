package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.FilialDto;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filialApi")
public class FilialController {
    @Autowired
    LoyihalashService loyihalashService;

    @PostMapping("/filialAdd/{kompaniyaId}")
    public HttpEntity<?> BankPost(@RequestBody FilialDto filialDto, @PathVariable Integer kompaniyaId){
        ApiResponse apiResponse =loyihalashService.addFilial(filialDto, kompaniyaId);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
}

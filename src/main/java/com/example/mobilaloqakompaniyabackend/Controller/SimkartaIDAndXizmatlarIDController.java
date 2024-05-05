package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.SimKartaDto;
import com.example.mobilaloqakompaniyabackend.DTO.SimkartaIDAndXizmatlarIDDto;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simkartaIDAndXizmatlarIDApi")
public class SimkartaIDAndXizmatlarIDController {
    @Autowired
    LoyihalashService loyihalashService;

    @PostMapping("/simkartaIDAndXizmatlarIDAdd/{xizmatId}/{simkartaId}")
    public HttpEntity<?> BankPost(@RequestBody SimkartaIDAndXizmatlarIDDto simkartaIDAndXizmatlarIDDto, @PathVariable Integer xizmatId, @PathVariable Integer simkartaId){
        ApiResponse apiResponse =loyihalashService.addsimkartaIdAndxizmatID(simkartaIDAndXizmatlarIDDto,xizmatId, simkartaId);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
}

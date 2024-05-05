package com.example.mobilaloqakompaniyabackend.Controller;

import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.DashboardDto;
import com.example.mobilaloqakompaniyabackend.DTO.KompaniyaDto;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboardApi")
public class DashboardController {
    @Autowired
    LoyihalashService loyihalashService;

//    @PostMapping("/dashboardAdd")
//    public HttpEntity<?> BankPost(@RequestBody DashboardDto dashboardDto, @PathVariable Integer tarifId){
//        ApiResponse apiResponse =loyihalashService.addDashboard(dashboardDto, tarifId);
//        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
//    }
}

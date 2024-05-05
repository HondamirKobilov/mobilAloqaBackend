package com.example.mobilaloqakompaniyabackend.Controller;
import com.example.mobilaloqakompaniyabackend.DTO.ApiResponse;
import com.example.mobilaloqakompaniyabackend.DTO.DaqiqaIshlatishDto;
import com.example.mobilaloqakompaniyabackend.DTO.PassportRaqamDto;
import com.example.mobilaloqakompaniyabackend.DTO.SimKartaDto;
import com.example.mobilaloqakompaniyabackend.ENTITIY.Users;
import com.example.mobilaloqakompaniyabackend.REPOSITORIY.UsersRepositoriy;
import com.example.mobilaloqakompaniyabackend.SERVICE.LoyihalashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/simkartaApi")
public class SimKartaController {
    @Autowired
    LoyihalashService loyihalashService;

    @Autowired
    UsersRepositoriy usersRepositoriy;

    @PostMapping("/simkartaAdd/{simkartaTuriId}/{shaxslarId}/{tariflarId}")
    public HttpEntity<?> simkartaPost(@RequestBody SimKartaDto simKartaDto, @PathVariable Integer simkartaTuriId, @PathVariable Integer shaxslarId, @PathVariable Integer tariflarId){
        ApiResponse apiResponse =loyihalashService.addsimkarta(simKartaDto, simkartaTuriId, shaxslarId, tariflarId);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }

    @PostMapping("/daqiqaIshlatish/{simkartaId}")
    public HttpEntity<?> daqiqaIshlatish(@RequestBody DaqiqaIshlatishDto daqiqaIshlatishDto, @PathVariable Integer simkartaId){
        ApiResponse apiResponse =loyihalashService.daqiqaIshlatish(daqiqaIshlatishDto,simkartaId);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }

    @PostMapping("/passportRaqam")
    public HttpEntity<?> PassportRaqam(@RequestBody PassportRaqamDto passportRaqamDto){
        Optional<Users> byPassportRaqam = usersRepositoriy.findByPassportRaqam(passportRaqamDto.getPassportRaqam());
        if(byPassportRaqam.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(byPassportRaqam.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mijoz ro'yhatdan o'tmagan");
    }
}

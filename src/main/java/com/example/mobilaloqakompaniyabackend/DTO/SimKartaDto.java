package com.example.mobilaloqakompaniyabackend.DTO;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Manzil;
import com.example.mobilaloqakompaniyabackend.ENTITIY.Users;
import com.example.mobilaloqakompaniyabackend.ENUM.MijozStatus;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class SimKartaDto {
    //Manzil
   private Manzil manzil;

    //User
    private Users users;

    private MijozStatus mijozStatus;

    private Integer mbSarfi;

    private Integer smsSarfi;

    private Integer daqiqaSarfi;

    private LocalDate tarifAmalqilishMuddati;

    private List<Integer> kartaTuri;
}

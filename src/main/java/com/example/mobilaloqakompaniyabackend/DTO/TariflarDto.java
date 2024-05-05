package com.example.mobilaloqakompaniyabackend.DTO;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Paketlar;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class TariflarDto {

    private String tarifNomi;

    private String tarifgaUtishNarxi;

    private double faollashtirishNarxi;

    private Integer birdaqNarx;

    private Integer birsmsNarx;

    private Integer birmbNarxi;

    private String amalQilishMuddati;

    //Paketlar

    private Paketlar paketlar;
}

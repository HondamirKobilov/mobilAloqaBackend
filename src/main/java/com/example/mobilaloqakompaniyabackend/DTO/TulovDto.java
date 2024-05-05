package com.example.mobilaloqakompaniyabackend.DTO;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class TulovDto {

    private LocalDate sana;

    private String tulovTuri;

    private double summa;
}

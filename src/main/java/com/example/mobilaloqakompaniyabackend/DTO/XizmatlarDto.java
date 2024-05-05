package com.example.mobilaloqakompaniyabackend.DTO;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class XizmatlarDto {

    private String xizmatNomi;

    private String xizmatNarxi;

    private LocalDate muddat;

    private Integer amalqilishKuni;

    private double mbHajmi;

    private double smsHajmi ;

    private double daqiqaHajmi;
}

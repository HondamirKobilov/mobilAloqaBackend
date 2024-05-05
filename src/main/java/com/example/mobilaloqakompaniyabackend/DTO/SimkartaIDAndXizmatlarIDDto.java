package com.example.mobilaloqakompaniyabackend.DTO;

import com.example.mobilaloqakompaniyabackend.ENTITIY.SimKarta;
import com.example.mobilaloqakompaniyabackend.ENTITIY.Xizmatlar;
import lombok.Data;


@Data
public class SimkartaIDAndXizmatlarIDDto {
    private Xizmatlar xizmatlar;
    private SimKarta simKarta;

    private double mbHajmi;

    private double smsHajmi ;

    private double daqiqaHajmi;
}

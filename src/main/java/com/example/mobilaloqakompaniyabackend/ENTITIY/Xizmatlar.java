package com.example.mobilaloqakompaniyabackend.ENTITIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Abstrakt.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Xizmatlar extends Abstrakt {

    @Column(nullable = false)
    private String xizmatNomi;

    @Column(nullable = false)
    private String xizmatNarxi;

    @Column(nullable = false)
    private LocalDate muddat;

    @Column(nullable = false)
    private Integer amalqilishKuni;

    @Column(nullable = false)
    private double mbHajmi;

    @Column(nullable = false)
    private double smsHajmi ;

    @Column(nullable = false)
    private double daqiqaHajmi;
}

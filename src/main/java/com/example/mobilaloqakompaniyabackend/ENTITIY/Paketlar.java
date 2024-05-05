package com.example.mobilaloqakompaniyabackend.ENTITIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Abstrakt.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Paketlar extends Abstrakt {

    @Column(nullable = false)
    private double mbHajmi;

    @Column(nullable = false)
    private double daqiqaHajmi;

    @Column(nullable = false)
    private double smsHajmi;
}

package com.example.mobilaloqakompaniyabackend.ENTITIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Abstrakt.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Detailzatsiya extends Abstrakt {

    @Column(nullable = false)
    private String raqam;

    @Column(nullable = false)
    private String daqiqa;

    @Column(nullable = false)
    private String sms;

    @OneToOne
    SimKarta simKarta;
}

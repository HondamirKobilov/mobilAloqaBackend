package com.example.mobilaloqakompaniyabackend.ENTITIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Abstrakt.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Manzil extends Abstrakt {

    @Column(nullable = false)
    private String viloyat;

    @Column(nullable = false)
    private String tuman;

    @Column(nullable = false)
    private String mfy;

    @Column(nullable = false)
    private String kucha;

    @Column(nullable = false)
    private String uyRaqam;

}

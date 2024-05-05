package com.example.mobilaloqakompaniyabackend.ENTITIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Abstrakt.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tariflar extends Abstrakt {

    @Column(nullable = false)
    private String tarifNomi;

    @Column(nullable = false)
    private String tarifgaUtishNarxi;

    @Column(nullable = false)
    private String amalQilishMuddati;

    @Column(nullable = false)
    private double faollashtirishNarxi;

    @Column(nullable = false)
    private Integer birdaqNarx;

    @Column(nullable = false)
    private Integer birsmsNarx;

    @Column(nullable = false)
    private Integer birmbNarxi;



    @OneToOne
    private Paketlar paketlar;
}

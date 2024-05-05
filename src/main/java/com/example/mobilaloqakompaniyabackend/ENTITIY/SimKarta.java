package com.example.mobilaloqakompaniyabackend.ENTITIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Abstrakt.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SimKarta extends Abstrakt {

    @Column(nullable = false)
    private String nomer;

    @Column(nullable = false)
    private double balans;

    @Column(nullable = false)
    private Integer mbSarfi;

    @Column(nullable = false)
    private Integer smsSarfi;

    @Column(nullable = false)
    private Integer daqiqaSarfi;

    @Column(nullable = false)
    private LocalDate tarifAmalqilishMuddati;


    @ManyToOne
    Users users;

    @OneToOne
    SimKartakod simKartakod;

    @OneToOne
    Tariflar tariflar;

    @ManyToOne
    Shaxslar shaxslar;

    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=false;
}

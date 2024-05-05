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
public class Tulov extends Abstrakt {

    @Column(nullable = false)
    private LocalDate sana;

    @Column(nullable = false)
    private String tulovTuri;

    @Column(nullable = false)
    private double summa;

    @OneToOne
    private SimKarta simKarta;
}

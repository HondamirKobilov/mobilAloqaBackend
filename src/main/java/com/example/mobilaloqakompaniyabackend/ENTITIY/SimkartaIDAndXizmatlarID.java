package com.example.mobilaloqakompaniyabackend.ENTITIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Abstrakt.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SimkartaIDAndXizmatlarID extends Abstrakt {

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private SimKarta simKarta;

    @ManyToOne
    private Xizmatlar xizmatlar;

    @Column(nullable = false)
    private double mbHajmi;

    @Column(nullable = false)
    private double smsHajmi ;

    @Column(nullable = false)
    private double daqiqaHajmi;

    @Column(nullable = false)
    private LocalDate muddat;

    @Column(nullable = false)
    private Integer amalqilishKuni;
}

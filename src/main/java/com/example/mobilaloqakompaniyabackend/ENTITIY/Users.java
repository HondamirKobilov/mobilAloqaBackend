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
public class Users extends Abstrakt {

    @Column(nullable = false)
    private String fish;

    @Column(nullable = false)
    private LocalDate tugilganYili;

    @Column(nullable = false)
    private String passportRaqam;

    @OneToOne
    private Manzil manzil;
}

package com.example.mobilaloqakompaniyabackend.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsersDto {

    private String fish;

    private LocalDate tugilganYili;

    private String passportRaqam;
}

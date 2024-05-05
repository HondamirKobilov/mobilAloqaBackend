package com.example.mobilaloqakompaniyabackend.DTO;

import com.example.mobilaloqakompaniyabackend.ENUM.Xuquqlar;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.List;

@Data
public class LavozimDto {

    private String nomi;
    private String izoh;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Xuquqlar> xuquqlarList;

}

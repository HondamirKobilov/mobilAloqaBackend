package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Manzil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManzilRepositoriy extends JpaRepository<Manzil, Integer> {
    boolean existsByViloyatAndTumanAndMfyAndKuchaAndUyRaqam(String viloyat, String tuman, String mfy, String kucha, String uyRaqam);

}
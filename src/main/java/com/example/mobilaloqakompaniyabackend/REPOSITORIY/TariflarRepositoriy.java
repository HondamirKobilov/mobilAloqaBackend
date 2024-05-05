package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Tariflar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariflarRepositoriy extends JpaRepository<Tariflar, Integer> {
    boolean existsByTarifNomi(String tarifNomi);
}

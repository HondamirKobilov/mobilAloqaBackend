package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.USSDkodlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface USSDkodlarRepositoriy extends JpaRepository<USSDkodlar, Integer> {
    Optional<USSDkodlar> findByUssdkodi(String ussdkodi);
}

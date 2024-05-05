package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.SimKartakod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimkartaKodRepositoriy extends JpaRepository<SimKartakod, Integer> {
    boolean existsByKod(String kod);
    Optional<SimKartakod> findById(Integer id);
}

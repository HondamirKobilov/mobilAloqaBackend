package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.SimKarta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimKartaRepositoriy extends JpaRepository<SimKarta, Integer> {
    Optional<SimKarta> findById(Integer id);
}

package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Shaxslar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShaxslarRepositoriy extends JpaRepository<Shaxslar, Integer> {
    boolean existsByShaxsTuri(String shaxsTuri);
    Optional<Shaxslar> findById(Integer id);
}

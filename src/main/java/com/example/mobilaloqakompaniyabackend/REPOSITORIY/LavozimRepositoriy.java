package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Lavozim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LavozimRepositoriy extends JpaRepository<Lavozim, Integer> {

    Optional<Lavozim> findByNomi(String nomi);
}

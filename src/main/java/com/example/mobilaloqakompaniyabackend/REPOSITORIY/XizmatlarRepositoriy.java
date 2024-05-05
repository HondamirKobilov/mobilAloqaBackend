package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Xizmatlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface XizmatlarRepositoriy extends JpaRepository<Xizmatlar, Integer> {
    Optional<Xizmatlar> findByXizmatNomi(String xizmatNomi);

    Optional<Xizmatlar> findById(Integer id);
}

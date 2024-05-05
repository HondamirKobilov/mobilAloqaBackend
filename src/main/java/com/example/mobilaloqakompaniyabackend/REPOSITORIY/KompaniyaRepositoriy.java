package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Kompaniya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KompaniyaRepositoriy extends JpaRepository<Kompaniya, Integer> {
    boolean existsByKompaniyaNomi(String kompaniyaNomi);
    Optional<Kompaniya> findById(Integer id);
}

package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Xodim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface XodimRepositoriy extends JpaRepository<Xodim, Integer> {
    Optional<Xodim> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByTelRaqam(String telRaqam);

    Optional<Xodim> findByUsernameAndEmailKod(String username, String emailKod);
}

package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepositoriy extends JpaRepository<Users, Integer> {
    Optional<Users> findByPassportRaqam(String passportRaqam);
    boolean existsByPassportRaqam(String passportRaqam);
}

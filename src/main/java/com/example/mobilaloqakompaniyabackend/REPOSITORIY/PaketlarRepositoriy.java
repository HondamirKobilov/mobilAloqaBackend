package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Paketlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaketlarRepositoriy extends JpaRepository<Paketlar, Integer> {
    boolean existsByMbHajmiAndDaqiqaHajmiAndSmsHajmi(double mbHajmi, double daqiqaHajmi, double smsHajmi);
}

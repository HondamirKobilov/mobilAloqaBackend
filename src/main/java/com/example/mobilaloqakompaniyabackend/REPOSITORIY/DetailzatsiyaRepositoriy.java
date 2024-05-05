package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Detailzatsiya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailzatsiyaRepositoriy extends JpaRepository<Detailzatsiya, Integer> {

}

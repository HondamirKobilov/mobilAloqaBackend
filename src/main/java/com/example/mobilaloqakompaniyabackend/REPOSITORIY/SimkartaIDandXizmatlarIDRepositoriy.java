package com.example.mobilaloqakompaniyabackend.REPOSITORIY;

import com.example.mobilaloqakompaniyabackend.ENTITIY.SimkartaIDAndXizmatlarID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimkartaIDandXizmatlarIDRepositoriy extends JpaRepository<SimkartaIDAndXizmatlarID, Integer> {

}

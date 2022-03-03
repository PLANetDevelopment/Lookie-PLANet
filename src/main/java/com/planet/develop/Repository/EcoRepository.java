package com.planet.develop.Repository;

import com.planet.develop.Entity.Eco;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EcoRepository extends JpaRepository<Eco, Long> {

    /**
     * 특정 지출 내역에 해당하는 친/반환경 데이터의 기본키 가져오기
     */
    @Query("select e.eno from Eco e " +
            "left join ExpenditureDetail ed on e.expenditure.eno = ed.eno " +
            "where e.expenditure.eno = :eno")
    List<Long> getEcoByEno(@Param("eno") Long eno);

}

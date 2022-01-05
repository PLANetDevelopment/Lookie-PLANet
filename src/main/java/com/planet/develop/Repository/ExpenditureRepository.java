package com.planet.develop.Repository;

import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

    /**
     * 특정 사용자의 친/반환경 별 지출 리스트 가져오기
     */
    @Query(" select e.user.userName, e.cost, ed.eco from Expenditure e " +
            "left join ExpenditureDetail ed on e.eno = ed.eno where e.user = :user and ed.eco = :eco")
    List<Object[]> getEcoList(@Param("user") User user, @Param("eco") EcoEnum eco);

}

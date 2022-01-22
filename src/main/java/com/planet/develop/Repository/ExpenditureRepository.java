package com.planet.develop.Repository;

import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

    /**
     * 특정 사용자의 하루 지출 리스트 가져오기
     */
    @Query("select e.user.userId, e.cost from Expenditure e " +
            "left join ExpenditureDetail ed on e.eno = ed.eno " +
            "where e.user = :user and e.date = :date")
    List<Object[]> getDayList(@Param("user") User user, @Param("date") LocalDate date);


    /**
     * 특정 사용자의 하루 친/반환경 별 지출 리스트 가져오기
     */
    @Query("select e.user.userId, e.cost, ed.eco from Expenditure e " +
            "left join ExpenditureDetail ed on e.eno = ed.eno " +
            "where e.user = :user and ed.eco = :eco and e.date = :date")
    List<Object[]> getDayEcoList(@Param("user") User user, @Param("eco") EcoEnum eco, @Param("date") LocalDate date);

    /**
     * 특정 사용자의 하루 지출 유형 별 지출 리스트 가져오기
     */
    @Query("select e.user.userId, e.cost, ed.exType, e.date from Expenditure e " +
            "left join ExpenditureDetail ed on e.eno = ed.eno " +
            "where e.user = :user and ed.exType = :exType and e.date = :date")
    List<Object[]> getDayExTypeList(@Param("user") User user, @Param("exType") money_Type exType, @Param("date") LocalDate date);

    /**
     * 특정 사용자의 하루 지출 방법 별 지출 리스트 가져오기
     */
    @Query("select e.user.userId, e.cost, ed.exWay, e.date from Expenditure e " +
            "left join ExpenditureDetail ed on e.eno = ed.eno " +
            "where e.user = :user and ed.exWay = :exWay and e.date = :date")
    List<Object[]> getDayExWayList(@Param("user") User user, @Param("exWay") money_Way exWay, @Param("date") LocalDate date);

}

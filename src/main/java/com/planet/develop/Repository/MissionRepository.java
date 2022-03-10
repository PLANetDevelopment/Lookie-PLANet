package com.planet.develop.Repository;


import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.Mission;
import com.planet.develop.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepository {
    private final EntityManager em;

    public Mission findMission(LocalDate date){
        int id = date.getDayOfMonth();
        return em.find(Mission.class,Long.valueOf(id));
    }
}

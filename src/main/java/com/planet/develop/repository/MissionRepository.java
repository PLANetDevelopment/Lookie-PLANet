package com.planet.develop.repository;

import com.planet.develop.entity.Mission;
import com.planet.develop.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MissionRepository{
        @PersistenceContext
        EntityManager em;

    public Long save(Mission mission){
        em.persist(mission);
        return mission.getId();
    }

    public Mission find(Long id){
        return em.find(Mission.class,id);
    }
}

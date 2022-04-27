package com.planet.develop.Repository;

import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.Mission;
import com.planet.develop.Entity.MissionComplete;
import com.planet.develop.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionCompleteRepository {
    private final EntityManager em;


    public void save(MissionComplete mission) {
        em.persist(mission);
    }

    public List<MissionComplete> findMissions(User user){
        return em.createQuery("select u from MissionComplete u where u.user= :user", MissionComplete.class)
                .setParameter("user",user)
                .getResultList();
    }

    public MissionComplete findOne(Long id) {
        return em.find(MissionComplete.class, id);
    }
    @Transactional
    public void delete(MissionComplete mission){
        MissionComplete findMission = findOne(mission.getId());
        em.remove(findMission);
    }
}

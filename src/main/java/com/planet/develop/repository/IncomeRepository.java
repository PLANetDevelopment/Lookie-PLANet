package com.planet.develop.repository;

import com.planet.develop.entity.Income;
import com.planet.develop.entity.Mission;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class IncomeRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Income income){
        em.persist(income);
        return income.getIno();
    }

    public Income find(Long id){
        return em.find(Income.class,id);
    }
}

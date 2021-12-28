package com.planet.develop.repository;

import com.planet.develop.entity.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(User user){
        em.persist(user);
        return user.getId();
    }

    public User find(Long id){
        return em.find(User.class,id);
    }
}

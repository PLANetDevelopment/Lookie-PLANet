package com.planet.develop.Repository;

import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IncomeRepository {
    private final EntityManager em;

    public void save(Income income) {
        em.persist(income);
    }

    public Income findOne(Long id) {
        return em.find(Income.class, id);
    }

    public List<Income> findDay(User user, LocalDate date){
        return em.createQuery("select u from Income u where u.user= :user and u.date= :date", Income.class)
                .setParameter("user",user)
                .setParameter("date",date)
                .getResultList();
    }


    public List<Income> findMonth(User findUser, int month) {

        LocalDate startDate = LocalDate.of(2022,month,1);
        LocalDate endDate = LocalDate.of(2022,month,startDate.lengthOfMonth());

        return em.createQuery("select u from Income u where :startDate<=u.date and u.date <= :endDate", Income.class)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }
}

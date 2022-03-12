package com.planet.develop.Repository;

import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IncomeRepository {
    private final EntityManager em;

    public void save(Income income) {
        em.persist(income);
    }

    public void delete(Income income){
        em.remove(income);
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


    public List<Income> findMonth(User user, int month) {

        LocalDate startDate = LocalDate.of(2022,month,1);
        LocalDate endDate = LocalDate.of(2022,month,startDate.lengthOfMonth());

        return em.createQuery("select u from Income u where u.user= :user and :startDate<=u.date and u.date <= :endDate", Income.class)
                .setParameter("user",user)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }

    /** 한 달 수입 조회 */
    public List<Income> findMonthDay(User user, int month) {

        LocalDate startDate = LocalDate.of(2022,month,1);
        int lengthOfMonth = startDate.lengthOfMonth();
        LocalDate endDate = LocalDate.of(2022,month,lengthOfMonth);

        return em.createQuery("select u from Income u where u.user= :user and :startDate<=u.date and u.date <= :endDate", Income.class)
                .setParameter("user",user)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }

    /** 한 달 특정 날짜까지 수입 조회 */
    public List<Income> findMonthDay(User user, int month, int day) {

        LocalDate startDate = LocalDate.of(2022,month,1);
        LocalDate endDate = LocalDate.of(2022,month,day);

        return em.createQuery("select u from Income u where u.user= :user and :startDate<=u.date and u.date <= :endDate", Income.class)
                .setParameter("user",user)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }

}
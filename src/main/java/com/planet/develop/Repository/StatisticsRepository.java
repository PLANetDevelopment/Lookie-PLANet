package com.planet.develop.Repository;

import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.Quote;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsRepository {
    private final EntityManager em;

    public Long getMonthEcoCount(User user,EcoEnum eco,int month){
        LocalDate startDate = LocalDate.of(2022,month,1);
        LocalDate endDate = LocalDate.of(2022,month,startDate.lengthOfMonth());

        return em.createQuery("select count(*) from Expenditure e " +
                        "left join ExpenditureDetail ed on e.eno = ed.eno " +
                        "left join Eco ec on e.eno = ec.expenditure.eno " +
                        "where e.user = :user and ec.eco = :eco and :startDate<=e.date and e.date <= :endDate", Long.class)
                .setParameter("user",user)
                .setParameter("eco",eco)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getSingleResult();
    }

    public Long getDifference(User user,LocalDate now, LocalDate last){
        LocalDate startDate = LocalDate.of( now.getYear(),now.getMonthValue(),1);
        Long nowCount = em.createQuery("select count(*) from Expenditure e " +
                        "left join ExpenditureDetail ed on e.eno = ed.eno " +
                        "left join Eco ec on e.eno = ec.expenditure.eno " +
                        "where e.user = :user and ec.eco ='G' and :startDate<=e.date and e.date <= :endDate", Long.class)
                .setParameter("user", user)
                .setParameter("startDate", startDate)
                .setParameter("endDate", now)
                .getSingleResult();

        startDate= startDate.minusMonths(1);
        Long lastCount = em.createQuery("select count(*) from Expenditure e " +
                        "left join ExpenditureDetail ed on e.eno = ed.eno " +
                        "left join Eco ec on e.eno = ec.expenditure.eno " +
                        "where e.user = :user and ec.eco ='G' and :startDate<=e.date and e.date <= :endDate", Long.class)
                .setParameter("user", user)
                .setParameter("startDate", startDate)
                .setParameter("endDate", last)
                .getSingleResult();
        System.out.println("now = " + now);
        System.out.println("last = " + last);
        System.out.println("nowCount = " + nowCount);
        System.out.println("lastCount = " + lastCount);
        return nowCount-lastCount;
    }
}

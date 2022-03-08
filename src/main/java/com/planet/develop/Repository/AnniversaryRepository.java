package com.planet.develop.Repository;

import com.planet.develop.Entity.Anniversary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnniversaryRepository {
    private final EntityManager em;

    public List<LocalDate> getAnniversaryList(int month){
        LocalDate startDate = LocalDate.of(2022,month,1);
        LocalDate endDate = LocalDate.of(2022,month,startDate.lengthOfMonth());

        return em.createQuery("select a.date from Anniversary a where :startDate<=a.date and a.date <= :endDate", LocalDate.class)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }

    public Anniversary getAnniversary(int month,int day){
        LocalDate date  = LocalDate.of(2022,month,day);
        return em.createQuery("select a from Anniversary a where :date=a.date", Anniversary.class)
                .setParameter("date",date)
                .getSingleResult();
    }

}

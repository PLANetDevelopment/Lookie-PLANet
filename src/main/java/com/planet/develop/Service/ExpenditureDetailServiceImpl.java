package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import com.planet.develop.Repository.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExpenditureDetailServiceImpl implements ExpenditureDetailService {

    private final ExpenditureRepository expenditureRepository;
    private final ExpenditureDetailRepository detailRepository;

    private final EntityManager em;

    @Override
    public Long register(ExpenditureDTO dto) {
        ExpenditureDetail entity = dtoToEntity(dto);
        detailRepository.save(entity);
        return entity.getEno();
    }

    @Override
    public String totalDayExType(User user, money_Type type, LocalDate date) {
        double total = 0;
        List<Object[]> exTypeList = expenditureRepository.getDayExTypeList(user, type, date);
        for (Object[] arr : exTypeList) {
            total += (double) arr[1];
        }
        return String.format("%.0f", total);
    }

    @Override
    public String totalDayEco(User user, EcoEnum eco, LocalDate date) {
        double total = 0;
        List<Object[]> ecoList = expenditureRepository.getDayEcoList(user, eco, date);
        for (Object[] arr : ecoList) {
            total += (double) arr[1];
        }
        return String.format("%.0f", total);
    }

    @Override
    public String totalDayExWay(User user, money_Way way, LocalDate date) {
        double total = 0;
        List<Object[]> exWayList = expenditureRepository.getDayExWayList(user, way, date);
        for (Object[] arr : exWayList) {
            total += (double) arr[1];
        }
        return String.format("%.0f", total);
    }

    public List<Expenditure> findMonthExpenditure(User user, int month) {
        LocalDate startDate = LocalDate.of(2022,month,1);
        int lengthOfMonth = startDate.lengthOfMonth();
        LocalDate endDate = LocalDate.of(2022,month,lengthOfMonth);
        return em.createQuery("select e from Expenditure e left join ExpenditureDetail ed on e.eno = ed.eno " +
                "where :startDate <= e.date and e.date <= :endDate " +
                "and e.user = :user", Expenditure.class)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .setParameter("user", user)
                .getResultList();
    }

}

package com.planet.develop.Service;

import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Repository.StatisticsRepository;
import com.planet.develop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final UserRepository userRepository;

    public Map<Integer,Long> getYearEcoCount(User user, EcoEnum eco, int year){
        Map<Integer,Long> result=new HashMap<>();
        for(int n=1;n<=12;n++){
            Long monthEcoCount =getMonthEcoCount(user,eco,year,n);
            if (monthEcoCount!=0)
                result.put(n,monthEcoCount);
        }
        return result;
    }


    public Long getMonthEcoCount(User user,EcoEnum eco,int year,int month){
        Long monthEcoCount = statisticsRepository.getMonthEcoCount(user, eco,year, month);
        return monthEcoCount;
    }

    public Map<String,Long> getEcoCountComparedToLast(User user, int year, int month){
        Map<String, Long> eco = new HashMap<>();
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate=LocalDate.now();
        if(LocalDate.now().getMonthValue()!=month)
            endDate = LocalDate.of(year,month,startDate.lengthOfMonth());

        LocalDate last=endDate.minusMonths(1);

        Long ecoCount=statisticsRepository.getNowEcoCount(user, endDate, startDate, EcoEnum.G);
        Long noneEcoCount=statisticsRepository.getNowEcoCount(user, endDate, startDate,EcoEnum.R);
        Long lastEcoCount=statisticsRepository.getLastEcoCount(user,last,startDate.minusMonths(1));
        Long difference = ecoCount-lastEcoCount;

        Long percentage=0L;
        if (ecoCount!=0 & noneEcoCount!=0){
            percentage = Math.round((double)ecoCount/(noneEcoCount+ecoCount)*100);
        }
        if (ecoCount!=0 & noneEcoCount==0){
            percentage=100L;
        }
        System.out.println("ecoCount = " + ecoCount);
        System.out.println("noneEcoCount = " + noneEcoCount);
        eco.put("difference",difference);
        eco.put("percentage",percentage);
        eco.put("nowEcoCount",ecoCount);
        eco.put("noneEcoCount",noneEcoCount);
        return eco;
    }
    public List<Map<money_Type,Long>> getTagCounts(User user, int year, int month){
        List<Map<money_Type,Long>> result=new ArrayList<>();
        Map<money_Type, Long> eco = new HashMap<>();
        Map<money_Type, Long> noEco = new HashMap<>();
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate=LocalDate.of(year, month, startDate.lengthOfMonth());
        System.out.println("endDate = " + endDate);
        money_Type[] categories = money_Type.values();
        for (money_Type category : categories) {
            System.out.println("category = " + category);
            eco.put(category,statisticsRepository.getCategoryTagCount(user, startDate, endDate, category, EcoEnum.G));
        }
        for (money_Type category : categories) {
            System.out.println("category = " + category);
            noEco.put(category,statisticsRepository.getCategoryTagCount(user, startDate, endDate, category, EcoEnum.R));
        }
        result.add(eco);
        result.add(noEco);
        return result;
    }
}

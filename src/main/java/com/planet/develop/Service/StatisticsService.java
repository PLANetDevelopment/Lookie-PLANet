package com.planet.develop.Service;

import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public Map<Integer,Long> getYearEcoCount(User user,EcoEnum eco,int year){
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

    public Map<String,Long> getEcoCountComparedToLast(User user,int year,int month){
        Map<String, Long> eco = new HashMap<>();
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate=LocalDate.now();
        if(LocalDate.now().getMonthValue()!=month)
            endDate = LocalDate.of(year,month,startDate.lengthOfMonth());

        LocalDate last=endDate.minusMonths(1);

        Long ecoCount=statisticsRepository.getNowEcoCount(user, endDate, startDate,EcoEnum.G);
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
    public  List<List<Object[]>> getFiveTagCounts(User user,int year,int month){
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate=LocalDate.of(year, month, startDate.lengthOfMonth());
        List<Object[]> categoryFiveTagCount = statisticsRepository.getCategoryFiveTagCount(user, startDate, endDate, EcoEnum.G);
        List<Object[]> categoryFiveNoTagCount = statisticsRepository.getCategoryFiveTagCount(user, startDate, endDate, EcoEnum.R);
        Long ecoCount=statisticsRepository.getNowEcoCount(user, endDate, startDate,EcoEnum.G);
        Long noneEcoCount=statisticsRepository.getNowEcoCount(user, endDate, startDate,EcoEnum.R);

        List<List<Object[]>> result=new ArrayList<>();
        List<Object[]> eco = new ArrayList<>();
        List<Object[]> noEco = new ArrayList<>();
        Long ecoCnt=0L;
        Long noEcoCnt=0L;
        for (Object[] tag : categoryFiveTagCount) {
            eco.add(tag);
            ecoCnt+=(Long)tag[1];
        }
        for (Object[] tag : categoryFiveNoTagCount) {
            noEco.add(tag);
            noEcoCnt+=(Long)tag[1];
        }
        Object eco_remain[]=new Object[2];
        eco_remain[0]="더보기";
        eco_remain[1] = ecoCount - ecoCnt;
        eco.add(eco_remain);
        eco_remain[1] = noneEcoCount-noEcoCnt;
        noEco.add(eco_remain);

        result.add(eco);
        result.add(noEco);
        return result;
    }
    public Map<money_Type,List<Long>> getTagCounts(User user,int year,int month,EcoEnum ecoEnum){
        Map<money_Type, List<Long>> counts = new HashMap<>();
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate=LocalDate.of(year, month, startDate.lengthOfMonth());
        money_Type[] categories = money_Type.values();
        Long totalTageCount = statisticsRepository.getMonthEcoCount(user,ecoEnum, year, month);
        for (money_Type category : categories) {
            List values = new ArrayList();
            Long tagCount = statisticsRepository.getCategoryTagCount(user, startDate, endDate, category, ecoEnum);
            double round = (double) tagCount / (double) totalTageCount *100;
            values.add(tagCount);
            values.add(round);
            counts.put(category,values);
        }

        //TODO % 큰 수대로 정렬
        return counts;
    }


}


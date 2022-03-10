package com.planet.develop.Service;

import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Repository.StatisticsRepository;
import com.planet.develop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final UserRepository userRepository;

    public Map<Integer,Long> getYearEcoCount(String userId,EcoEnum eco){
        User user = userRepository.findById(userId).get();
        Map<Integer,Long> result=new HashMap<>();
        for(int n=1;n<=12;n++){
            Long monthEcoCount =getMonthEcoCount(user,eco,n);
            if (monthEcoCount!=0)
                result.put(n,monthEcoCount);
        }
        return result;
    }

    public Long getMonthEcoCount(User user,EcoEnum eco,int month){
        Long monthEcoCount = statisticsRepository.getMonthEcoCount(user, eco, month);
        return monthEcoCount;
    }

    public Long getEcoCountComparedToLast(String user_id){
        User user = userRepository.findById(user_id).get();
        LocalDate now = LocalDate.now();
        LocalDate last=LocalDate.now().minusMonths(1);
        Long difference = statisticsRepository.getDifference(user, now, last);
        return difference;
    }

}


package com.planet.develop.Controller;

import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.MissionComplete;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Repository.IncomeRepository;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class StatisticsController {
    private final UserRepository userRepository;
    private final StatisticsService statisticsService;
    private final IncomeService incomeService;
    private final ExpenditureDetailService expenditureDetailService;
    /** 친/반환경 태그 통계 */
    @GetMapping("/statistics/{id}/{year}/{month}")
    public Result main(@PathVariable("id") String userId,@PathVariable("year") int year,@PathVariable("month") int month){
        User user = userRepository.findById(userId).get();
        Long incomeTotal = incomeService.totalMonth(user,year, month);
        Long expenditureTotal = expenditureDetailService.totalMonth(user,year,month);
        Map<String,Long> ecoBoard =statisticsService.getEcoCountComparedToLast(user,year,month);
        Map<Integer, Long> ecoCount = statisticsService.getYearEcoCount(user, EcoEnum.G,year);
        List<Map<money_Type, Long>> tagCounts = statisticsService.getTagCounts(user, year, month);
        Long difference = ecoBoard.get("difference");
        Long percentage = ecoBoard.get("percentage");
        Long nowEcoCount=ecoBoard.get("nowEcoCount");
        Long nowNoneEcoCount=ecoBoard.get("noneEcoCount");
        Map<money_Type, Long> ecoTagCounts=tagCounts.get(0);
        Map<money_Type, Long> noEcoTagCounts=tagCounts.get(1);
        return new Result(incomeTotal,expenditureTotal,difference,ecoCount,nowEcoCount,nowNoneEcoCount,percentage,ecoTagCounts,noEcoTagCounts);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T incomeTotal;
        private T expenditureTotal;
        private T difference;
        private T ecoCount;
        private T nowEcoCount;
        private T nowNoneEcoCount;
        private T percentage;
        private T ecoTagCounts;
        private T noEcoTagCounts;
    }
}

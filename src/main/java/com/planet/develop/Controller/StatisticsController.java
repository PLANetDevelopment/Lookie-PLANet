package com.planet.develop.Controller;

import com.planet.develop.Entity.MissionComplete;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Service.StatisticsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class StatisticsController {
    private final StatisticsService statisticsService;

    /** 친/반환경 태그 통계 */
    @GetMapping("/statistics/{id}")
    public Result main(@PathVariable("id") String userId){
        Long difference = statisticsService.getEcoCountComparedToLast(userId);
        Map<Integer, Long> ecoCount = statisticsService.getYearEcoCount(userId, EcoEnum.G);
        Map<Integer, Long> noneEcoCount = statisticsService.getYearEcoCount(userId, EcoEnum.N);

        LocalDate date = LocalDate.of(2022,1,11);
        LocalDate localDate = date.minusMonths(1);
        System.out.println("localDate = " + localDate);
        return new Result(difference,ecoCount,noneEcoCount);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T difference;
        private T ecoCount;
        private T noneEcoCount;
    }
}

package com.planet.develop.Controller;

import com.planet.develop.DTO.StatisticsDto;
import com.planet.develop.Enum.TIE;
import com.planet.develop.Service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /** 지난 달 대비 수입/지출 차액 + 한 달 일별 상세 내역 페이지 */
    @GetMapping("/api/statistics/total/{id}/{month}")
    public StatisticsDto findTotalStatic(@PathVariable("id") String id, @PathVariable("month") int month){
        return statisticsService.functionByMonth(id, month, TIE.T);
    }

    /** 비고) 수입 페이지 */
    @GetMapping("/api/statistics/income/{id}/{month}")
    public StatisticsDto findIncomeStatic(@PathVariable("id") String id, @PathVariable("month") int month){
        return statisticsService.functionByMonth(id, month, TIE.I);
    }

    /** 비고) 지출 페이지 */
    @GetMapping("/api/statistics/expenditure/{id}/{month}")
    public StatisticsDto findExpenditureStatic(@PathVariable("id") String id, @PathVariable("month") int month){
        return statisticsService.functionByMonth(id, month, TIE.E);
    }

}

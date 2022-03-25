package com.planet.develop.Controller;

import com.planet.develop.DTO.StatisticsDto;
import com.planet.develop.DTO.StatisticsEcoDto;
import com.planet.develop.Enum.TIE;
import com.planet.develop.Service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;

    /** 지난 달 대비 수입/지출 차액 + 한 달 일별 상세 내역 페이지 */
    @GetMapping("/api/statistics/total/{id}/{year}/{month}")
    public StatisticsDto findTotalStatistics(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsService.functionByMonth(id, year, month, TIE.T);
    }

    /** 비고) 수입 내역 필터링 */
    @GetMapping("/api/statistics/total/income/{id}/{year}/{month}")
    public StatisticsDto filteringIncome(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsService.functionByMonth(id, year, month, TIE.I);
    }

    /** 비고) 지출 내역 페이지 */
    @GetMapping("/api/statistics/total/expenditure/{id}/{year}/{month}")
    public StatisticsDto filteringExpenditure(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsService.functionByMonth(id, year, month, TIE.E);
    }

    /** 수입 페이지 */
    @GetMapping("/api/statistics/income/{id}/{year}/{month}")
    public StatisticsDto findIncomeStatistics(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsService.functionByMonth(id, year, month, TIE.I);
    }

    /** 지출 페이지 */
    @GetMapping("/api/statistics/expenditure/{id}/{year}/{month}")
    public StatisticsEcoDto findExpenditureStatistics(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsService.functionEcoByMonth(id, year, month);
    }

}

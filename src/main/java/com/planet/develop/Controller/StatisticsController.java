package com.planet.develop.Controller;

import com.planet.develop.DTO.StatisticsDto;
import com.planet.develop.DTO.StatisticsEcoDto;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.TIE;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Service.ExpenditureDetailService;
import com.planet.develop.Service.IncomeService;
import com.planet.develop.Service.StatisticsDetailService;
import com.planet.develop.Service.StatisticsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class StatisticsController {

    private final StatisticsDetailService statisticsDetailService;
    private final UserRepository userRepository;
    private final IncomeService incomeService;
    private final StatisticsService statisticsService;
    private final ExpenditureDetailService expenditureDetailService;

    /** 친/반환경 태그 통계 */
    @GetMapping("/statistics/{id}/{year}/{month}")
    public Result main(@PathVariable("id") String userId,@PathVariable("year") int year,@PathVariable("month") int month){
        User user = userRepository.findById(userId).get();
        Long incomeTotal = incomeService.totalMonth(user,year, month);
        Long expenditureTotal = expenditureDetailService.totalMonth(user,year,month);
        Map<String,Long> ecoBoard = statisticsService.getEcoCountComparedToLast(user,year,month);
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

    /** 지난 달 대비 수입/지출 차액 + 한 달 일별 상세 내역 페이지 */
    @GetMapping("/api/statistics/total/{id}/{year}/{month}")
    public StatisticsDto findTotalStatistics(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsDetailService.functionByMonth(id, year, month, TIE.T);
    }

    /** 비고) 수입 내역 필터링 */
    @GetMapping("/api/statistics/total/income/{id}/{year}/{month}")
    public StatisticsDto filteringIncome(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsDetailService.functionByMonth(id, year, month, TIE.I);
    }

    /** 비고) 지출 내역 페이지 */
    @GetMapping("/api/statistics/total/expenditure/{id}/{year}/{month}")
    public StatisticsDto filteringExpenditure(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsDetailService.functionByMonth(id, year, month, TIE.E);
    }

    /** 수입 페이지 */
    @GetMapping("/api/statistics/income/{id}/{year}/{month}")
    public StatisticsDto findIncomeStatistics(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsDetailService.functionByMonth(id, year, month, TIE.I);
    }

    /** 지출 페이지 */
    @GetMapping("/api/statistics/expenditure/{id}/{year}/{month}")
    public StatisticsEcoDto findExpenditureStatistics(@PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        return statisticsDetailService.functionEcoByMonth(id, year, month);
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
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@PreAuthorize("permitAll()") // 모든 사용자가 접근 가능
public class StatisticsController {

    private final StatisticsDetailService statisticsDetailService;
    private final UserRepository userRepository;
    private final IncomeService incomeService;
    private final StatisticsService statisticsService;
    private final ExpenditureDetailService expenditureDetailService;

    /** 친/반환경 태그 통계 */
    @GetMapping("/statistics/{id}/{year}/{month}")
    public Result statistics(@PathVariable("id") String userId,@PathVariable("year") int year,@PathVariable("month") int month){
        User user = userRepository.findById(userId).get();
        Long incomeTotal = incomeService.totalMonth(user,year, month);
        Long expenditureTotal = expenditureDetailService.totalMonth(user,year,month);
        Map<String,Object> ecoBoard = statisticsService.getEcoCountComparedToLast(user,year,month);
        Map<Integer, Long> ecoCount = statisticsService.getYearEcoCount(user, EcoEnum.G,year);
        List<List<Object[]>> fiveTagCounts = statisticsService.getFiveTagCounts(user, year, month);
        Object ecoDifference = ecoBoard.get("ecoDifference");
        Object noEcoDifference = ecoBoard.get("noEcoDifference");
        Object percentage = ecoBoard.get("percentage");
        Object nowEcoCount=ecoBoard.get("nowEcoCount");
        Object nowNoneEcoCount=ecoBoard.get("noneEcoCount");
        List<Object[]> ecoTagCounts=fiveTagCounts.get(0);
        List<Object[]> noEcoTagCounts=fiveTagCounts.get(1);
        return new Result(user.getUserName(),incomeTotal,expenditureTotal,ecoDifference,noEcoDifference,ecoCount,nowEcoCount,nowNoneEcoCount,percentage,ecoTagCounts,noEcoTagCounts);
    }

    /** 친환경 태그 통계 */
    @GetMapping("/statistics/ecoCountsDetail/{id}/{year}/{month}")
    public statisticsEcoRequestDto statisticsEcoDetail(@PathVariable("id") String userId,@PathVariable("year") int year,@PathVariable("month") int month) {
        User user = userRepository.findById(userId).get();
        List<Object[]> tagCategoryList = statisticsService.getTagCategoryList(user, year, month, EcoEnum.G);

        return new statisticsEcoRequestDto(tagCategoryList);
    }

    /** 반환경 태그 통계 */
    @GetMapping("/statistics/noEcoCountsDetail/{id}/{year}/{month}")
    public statisticsEcoRequestDto statisticsNoEcoDetail(@PathVariable("id") String userId,@PathVariable("year") int year,@PathVariable("month") int month) {
        User user = userRepository.findById(userId).get();
        List<Object[]> tagCategoryList = statisticsService.getTagCategoryList(user, year, month, EcoEnum.R);
        return new statisticsEcoRequestDto(tagCategoryList);
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
        private T userName;
        private T incomeTotal;
        private T expenditureTotal;
        private T ecoDifference;
        private T noEcoDifference;
        private T ecoCount;
        private T nowEcoCount;
        private T nowNoneEcoCount;
        private T percentage;
        private T ecoTagCounts;
        private T noEcoTagCounts;
    }

    @Data
    @AllArgsConstructor
    static class statisticsEcoRequestDto<T>{
        private T tagList;
    }


}
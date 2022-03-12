package com.planet.develop.Service;

import com.planet.develop.DTO.*;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.TIE;
import com.planet.develop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final CalendarService calendarService;
    private final IncomeService incomeService;
    private final ExpenditureDetailService expenditureDetailService;
    private final UserRepository userRepository;

    /** 이번 달 지출/수입 총액 계산 + 지날 달 대비 현재 달의 수입/지출 차액 계산 */
    @Override
    public StatisticsDto CalculDif(String id, int month, int currDay, int lastDay) {
        CalendarDto thisMonth = totalMonthDay(id, month, currDay); // 이번 달
        CalendarDto lastMonth = totalMonthDay(id, month-1, lastDay); // 지난 달
        
        Long inDif = thisMonth.getTotalMonthIncome() - lastMonth.getTotalMonthIncome(); // 수입 차액
        Long exDif = thisMonth.getTotalMonthExpenditure() - lastMonth.getTotalMonthExpenditure(); // 지출 차액
        
        StatisticsDto statisticsDto = new StatisticsDto(
                thisMonth.getTotalMonthIncome(), thisMonth.getTotalMonthExpenditure(), inDif, exDif);
        
        if (inDif >= 0) statisticsDto.setInMore(true); // 이번 달 수입이 더 많다.
        else statisticsDto.setInDif(inDif*(-1)); // 음수가 나올 경우 절대값으로 저장
        if (exDif >= 0) statisticsDto.setExMore(true); // 이번 달 지출이 더 많다.
        else statisticsDto.setExDif(exDif*(-1));

        return statisticsDto;
    }

    /** 한 달 중 특정 날짜까지의 총 지출/수입 */
    @Override
    public CalendarDto totalMonthDay(String id, int month, int day) {
        Long totalMonthIncome = incomeService.totalMonthDay(id,month, day); // 총 수입
        User user = userRepository.findById(id).get();
        Long totalMonthExpenditure = expenditureDetailService.totalMonthDay(user, month, day); // 총 지출
        return new CalendarDto(totalMonthIncome,totalMonthExpenditure);
    }

    /** 한 달 일별 수입/지출 상세 조회 */
    @Override
    public List<StatisticsDayDetailDto> findDayDetail(String id, int month, TIE tie) {
        int days = LocalDate.of(2022,month,month).lengthOfMonth(); // 날짜 세기
        List<StatisticsDayDetailDto> detailDtoList = new ArrayList<>(); // 반환할 리스트
        for(int day=1; day<=days; day++) { // 한 달 상세 내역 조회
            StatisticsDayDetailDto dto = new StatisticsDayDetailDto();
            LocalDate date = LocalDate.of(2022, month, day); // 날짜
            List<TypeDetailDto> list = calendarService.inExTypeDetailDto(id, month, day, tie); // 일별 수입 상세 내역 조회
            dto.changeDate(date); dto.setDetailDtoList(list); // 날짜와 상세 내역을 하나의 dto로 담는다.
            detailDtoList.add(dto); // 일별 상세 내역을 리스트에 담는다.
        }
        return detailDtoList;
    }
    
    /** CalculDif() 함수에서 얻은 데이터와 findDayDetail() 함수에서 얻은 데이터를 하나의 StatisticsDto로 합친다 */
    @Override
    public StatisticsDto merge(String id, int month, int currDay, int lastDay, TIE tie) {
        StatisticsDto statisticsDto = CalculDif(id, month, currDay, lastDay);
        List<StatisticsDayDetailDto> list = findDayDetail(id, month, tie);
        statisticsDto.setDetailDtoList(list);
        return statisticsDto;
    }

    /** 현재 월이라면 '지난 월 이맘때보다~' & 지난 월이라면 '지난 월보다~' */
    public StatisticsDto functionByMonth(String id, int month, TIE tie) {
        int lastDayOfMonth = LocalDate.of(2022, month, month).lengthOfMonth(); // 조회 달 마지막 날짜
        int lastDayOfLastMonth = LocalDate.of(2022, month-1, month-1).lengthOfMonth(); // 지난 달 마지막 날짜
        int today = LocalDate.now().getDayOfMonth(); // 오늘 날짜

        if (month == (int) LocalDate.now().getMonthValue()) { // 조회하는 월이 현재 월이라면
            if (today > lastDayOfLastMonth) { // 12월 31일에 조회한다면 -> 11월 30일까지 조회해서 비교
                log.info("step 1 is started...");
                return merge(id, month, today, lastDayOfMonth, tie);
            } else return merge(id, month, today, today, tie); // 12월 15일에 조회한다면 -> 11월 15일까지 조회해서 비교
        } else return merge(id, month, lastDayOfMonth, lastDayOfLastMonth, tie); // 조회하는 월이 현재 월이 아니라면 지난 달 총 수입/지출과 비교해서 계산
    }

}

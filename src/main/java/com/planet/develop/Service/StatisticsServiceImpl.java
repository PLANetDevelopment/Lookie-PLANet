package com.planet.develop.Service;

import com.planet.develop.DTO.*;
import com.planet.develop.Enum.TIE;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private final CalendarService calendarService;

    /** 이번 달 지출/수입 총액 계산 + 지날 달 대비 현재 달의 수입/지출 차액 계산 */
    @Override
    public StatisticsDto CalculDif(String id, int month) {
        // TODO: 만약 이번 달이라면 이번 달이 며칠인지에 따라 지난 달도 해당 날짜까지의 총합이 돼야 한다.
        CalendarDto thisMonth = calendarService.findCalendar(id, month); // 이번 달
        CalendarDto lastMonth = calendarService.findCalendar(id, month-1); // 지난 달
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
    public StatisticsDto merge(String id, int month, TIE tie) {
        StatisticsDto statisticsDto = CalculDif(id, month);
        List<StatisticsDayDetailDto> list = findDayDetail(id, month, tie);
        statisticsDto.setDetailDtoList(list);
        return statisticsDto;
    }

}

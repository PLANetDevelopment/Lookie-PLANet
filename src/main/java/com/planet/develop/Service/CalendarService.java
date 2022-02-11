package com.planet.develop.Service;

import com.planet.develop.DTO.CalendarDto;
import com.planet.develop.DTO.DayDetailDto;
import com.planet.develop.DTO.DayExTypeDetailDto;
import com.planet.develop.Enum.money_Type;

import java.util.List;
import java.util.Map;

public interface CalendarService {
    /** 월별 지출/수입 총액과 일별 지출/소비 총액 구하기 */
    CalendarDto findCalendar(String id, int month);
    /** 일별 지출/수입 내역 구하기 */
    DayDetailDto findDayDetail(String id, int month, int day);
    /** 유형별 하루 지출/수입 상세 */
    Map<money_Type, List<DayExTypeDetailDto>> findDayExTypeDetail(String id, int month, int day);
}

package com.planet.develop.Service;

import com.planet.develop.DTO.CalendarDto;

public interface CalendarService {
    /** 월별 지출/수입 총액과 일별 지출/소비 총액 구하기 */
    CalendarDto findCalendar(String id, int month);
}

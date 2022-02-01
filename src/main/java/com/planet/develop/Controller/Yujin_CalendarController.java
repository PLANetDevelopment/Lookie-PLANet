package com.planet.develop.Controller;

import com.planet.develop.DTO.CalendarDto;
import com.planet.develop.DTO.DayDetailDto;
import com.planet.develop.Service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Yujin_CalendarController {

    private final CalendarService calendarService;

    /** 월별 조회 함수 */
    @GetMapping("/api/calendar/{id}/{month}")
    public CalendarDto findCalendar(@PathVariable("id") String id, @PathVariable("month") int month){
        CalendarDto calendar = calendarService.findCalendar(id, month);
        return calendar;
    }

    /** 일별 조회 (세부 조회) */
    @GetMapping("/api/calendar/{id}/{month}/{day}")
    public DayDetailDto findIncomeDetail(@PathVariable("id") String id,
                                                  @PathVariable("month") int month, @PathVariable("day") int day){
        DayDetailDto dayDetailDto = calendarService.findDayDetail(id, month, day);
        return dayDetailDto;
    }
}
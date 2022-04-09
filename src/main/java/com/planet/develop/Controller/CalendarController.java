package com.planet.develop.Controller;

import com.planet.develop.DTO.*;
import com.planet.develop.Entity.Anniversary;
import com.planet.develop.Entity.Quote;
import com.planet.develop.Repository.AnniversaryRepository;
import com.planet.develop.Repository.QuoteRepository;
import com.planet.develop.Service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@RequiredArgsConstructor
@RestController
@PreAuthorize("permitAll()") // 모든 사용자가 접근 가능
public class CalendarController {

    private final CalendarService calendarService;
    private final QuoteRepository quoteRepository;
    private final AnniversaryRepository anniversaryRepository;
    Random random = new Random();

    /** 월별 수입/지출 조회 함수 */
    @GetMapping("/calendar/{id}/{year}/{month}")
    public CalendarResponseDto findCalendar(@PathVariable("id") String id,@PathVariable("year") int  year,@PathVariable("month") int month){
        CalendarDto calendar = calendarService.findCalendar(id,year,month);
        int qno = random.nextInt(40) + 1;
        Quote quote = quoteRepository.findById(qno).get();
        Optional<List<Object[]>> anniversaryList = Optional.ofNullable(anniversaryRepository.getAnniversaryList(month));
        return new CalendarResponseDto(anniversaryList,calendar, quote.getContent());
    }


    /** 일별 조회 (세부 조회) */
    @GetMapping("/calendar/{id}/{year}/{month}/{day}")
    public Result findIncomeDetail(@PathVariable("id") String id,@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day){

        return  calendarService.findDayExTypeDetail(id,year,month,day);
    }
}

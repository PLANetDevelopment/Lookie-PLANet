package com.planet.develop.Controller;

import com.planet.develop.DTO.CalendarDayDto;
import com.planet.develop.DTO.CalendarDto;
import com.planet.develop.DTO.IncomeDetailDto;
import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Service.ExpenditureDetailService;
import com.planet.develop.Service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
public class Yujin_CalendarController {

    private final IncomeService incomeService;
    private final ExpenditureDetailService detailService;
    private final UserRepository userRepository;

    /** 월별 조회 함수 */
    @GetMapping("/api/calendar/{id}/{month}")
    public CalendarDto findIncome(@PathVariable("id") String id, @PathVariable("month") int month){
        Long totalMonthIncome = incomeService.totalMonth(id,month);
        System.out.println("total: " + totalMonthIncome);
        User user = userRepository.findById(id).get();
        System.out.println("user: " + user);
        Long totalMonthExpenditure = detailService.totalMonth(user, month);
        System.out.println("total: " + totalMonthExpenditure);
        List<CalendarDayDto> calendarDayDtos = new ArrayList<>();
        for(int n=1;n<=31;n++) {
            Long incomeDay = incomeService.totalDay(id, LocalDate.of(2022, month,n));
            Long expenditureDay = detailService.totalDay(user, LocalDate.of(2022, month, n));
            CalendarDayDto calendarDayDto = new CalendarDayDto(LocalDate.of(2022, month,n), incomeDay, expenditureDay);
            calendarDayDtos.add(calendarDayDto);
        }
        return new CalendarDto(totalMonthIncome,totalMonthExpenditure, calendarDayDtos);
    }


    /** 일별 조회 (세부 조회) */
    @GetMapping("/api/calendar/{id}/{month}/{day}")
    public List<IncomeDetailDto> findIncomeDetail(@PathVariable("id") String id, @PathVariable("month") int month, @PathVariable("day") int day){
        List<Income> days = incomeService.findDay(id, LocalDate.of(2022, month, day));

        List<IncomeDetailDto> collect = days.stream()
                .map(m->new IncomeDetailDto(m.getIn_cost(),m.getIn_way(),m.getIn_type(),m.getMemo()))
                .collect(Collectors.toList());

        return collect;
    }
}
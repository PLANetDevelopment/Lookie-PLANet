package com.planet.develop.Service;

import com.planet.develop.DTO.CalendarDayDto;
import com.planet.develop.DTO.CalendarDto;
import com.planet.develop.Entity.User;
import com.planet.develop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CalendarServiceImpl implements CalendarService {

    private final IncomeService incomeService;
    private final ExpenditureDetailService detailService;
    private final UserRepository userRepository;

    @Override
    public CalendarDto findCalendar(String id, int month) {
        Long totalMonthIncome = incomeService.totalMonth(id,month);
        User user = userRepository.findById(id).get();
        Long totalMonthExpenditure = detailService.totalMonth(user, month);
        List<CalendarDayDto> calendarDayDtos = new ArrayList<>();
        for(int n=1;n<=31;n++) {
            Long incomeDay = incomeService.totalDay(id, LocalDate.of(2022, month,n));
            Long expenditureDay = detailService.totalDay(user, LocalDate.of(2022, month, n));
            CalendarDayDto calendarDayDto = new CalendarDayDto(LocalDate.of(2022, month,n), incomeDay, expenditureDay);
            calendarDayDtos.add(calendarDayDto);
        }
        return new CalendarDto(totalMonthIncome,totalMonthExpenditure, calendarDayDtos);
    }

}

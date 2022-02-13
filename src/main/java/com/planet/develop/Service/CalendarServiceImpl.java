package com.planet.develop.Service;

import com.planet.develop.DTO.*;
import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CalendarServiceImpl implements CalendarService {

    private final IncomeService incomeService;
    private final ExpenditureDetailService detailService;
    private final UserRepository userRepository;

    /** 1일-31일 동안 하루 지출/수입 */
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

    /** 하루 지출/수입 상세 */
    @Override
    public DayDetailDto findDayDetail(String id, int month, int day) {
        List<Income> in_days = incomeService.findDay(id, LocalDate.of(2022, month, day));
        User user = userRepository.findById(id).get();
        List<ExpenditureDetailDto> ex_days = detailService.findDay(user, LocalDate.of(2022, month, day));
        List<IncomeDetailDto> in_collect = in_days.stream()
                .map(m->new IncomeDetailDto(m.getIn_cost(),m.getIn_way(),m.getIn_type(),m.getMemo()))
                .collect(Collectors.toList());
        DayDetailDto dayDetailDto = new DayDetailDto(in_collect, ex_days);
        return dayDetailDto;
    }

    /** 유형별 하루 지출/수입 상세 */
    public Map<money_Type, List<DayExTypeDetailDto>> findDayExTypeDetail(String id, int month, int day) {
        User user = userRepository.findById(id).get();
        List<ExpenditureDetailDto> ex_days = detailService.findDay(user, LocalDate.of(2022, month, day));

        List<DayExTypeDetailDto> detailDtos = new ArrayList<>();
        for (ExpenditureDetailDto dto : ex_days) { // 타입 변환
            DayExTypeDetailDto typeDto = new DayExTypeDetailDto(
                    dto.getExType(), dto.getCost(), dto.getMemo(),dto.getEco(), dto.getEcoDetail());
            detailDtos.add(typeDto);
        }

        Map<money_Type, List<DayExTypeDetailDto>> map = new HashMap<>();
        for (DayExTypeDetailDto dto : detailDtos){
            money_Type key = dto.getExType();
            if (map.containsKey(key)) {
                List<DayExTypeDetailDto> list = map.get(key);
                list.add(dto);
            } else {
                List<DayExTypeDetailDto> list = new ArrayList<>();
                list.add(dto);
                map.put(key, list);
            }
        }
        return map;
    }

}

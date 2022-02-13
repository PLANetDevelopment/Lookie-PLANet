package com.planet.develop.Controller;

import com.planet.develop.DTO.CalendarDto;
import com.planet.develop.DTO.DayExTypeDetailDto;
import com.planet.develop.DTO.IncomeTypeDetailDto;
import com.planet.develop.DTO.TypeDetailDto;
import com.planet.develop.Entity.Income;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Service.CalendarService;
import com.planet.develop.Service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class Yujin_CalendarController {

    private final CalendarService calendarService;
    private final IncomeService incomeService;

    /** 월별 수입/지출 조회 함수 */
    @GetMapping("/api/calendar/{id}/{month}")
    public CalendarDto findCalendar(@PathVariable("id") String id, @PathVariable("month") int month){
        CalendarDto calendar = calendarService.findCalendar(id, month);
        return calendar;
    }

    /** 일별 조회 (세부 조회) */
    @GetMapping("/api/calendar/{id}/{month}/{day}")
    public Map<money_Type, List<TypeDetailDto>> findIncomeDetail(@PathVariable("id") String id,
                                                                 @PathVariable("month") int month, @PathVariable("day") int day){
        return calendarService.findDayExTypeDetail(id, month, day);
    }

//    /** 일별 조회 (세부 조회) */
//    @GetMapping("/api/income/{id}/{month}/{day}")
//    public Map<String, IncomeTypeDetailDto> findIncomeDetail(@PathVariable("id") String id,
//                                                             @PathVariable("month") int month,
//                                                             @PathVariable("day") int day){
//        List<Income> in_days = incomeService.findDay(id, LocalDate.of(2022, month, day));
//
//        List<IncomeTypeDetailDto> collect = days.stream()
//                .map(m->new IncomeTypeDetailDto(m.getIn_cost(),m.getIn_way(),m.getIn_type(),m.getMemo()))
//                .collect(Collectors.toList());
//
//        Map incomeDetails = sortByType(collect);
//        return incomeDetails;
//    }
//
//    private Map sortByType(List<IncomeTypeDetailDto> collect) {
//        List<IncomeTypeDetailDto> salary=new ArrayList<>();
//        List<IncomeTypeDetailDto> allowance=new ArrayList<>();
//        List<IncomeTypeDetailDto> etc=new ArrayList<>();
//
//        for (IncomeTypeDetailDto detailDto : collect) {
//            if (detailDto.getIn_type().equals(money_Type.salary))
//                salary.add(detailDto);
//            else if (detailDto.getIn_type().equals(money_Type.allowance))
//                allowance.add(detailDto);
//            else if (detailDto.getIn_type().equals(money_Type.etc))
//                etc.add(detailDto);
//        }
//
//        Map types = new HashMap();
//        types.put("salary", salary);
//        types.put("allowance", allowance);
//        types.put("etc", etc);
//
//        return types;
//    }

}
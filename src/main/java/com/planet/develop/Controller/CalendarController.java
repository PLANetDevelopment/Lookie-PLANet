package com.planet.develop.Controller;

import com.planet.develop.DTO.IncomeDetailDto;
import com.planet.develop.DTO.IncomeDto;
import com.planet.develop.Entity.Income;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
public class CalendarController {

    private final IncomeService incomeService;

    /** 월별 조회 함수 */
    @GetMapping("/api/income/{id}/{month}")
    public IncomeDto findIncome(@PathVariable("id") String id, @PathVariable("month") int month){
        Long totalMonth = incomeService.totalMonth(id,month);

        List<Long> days = new ArrayList<>();
        days.add(0L);
        for(int n=1;n<=31;n++) {
            Long day = incomeService.totalDay(id, LocalDate.of(2022, month,n));
            days.add(day);
        }
        return new IncomeDto(totalMonth,days);

    }


    /** 일별 조회 (세부 조회) */
    @GetMapping("/api/income/{id}/{month}/{day}")
    public Map<String,IncomeDetailDto> findIncomeDetail(@PathVariable("id") String id,@PathVariable("month") int month,@PathVariable("day") int day){
        List<Income> days = incomeService.findDay(id, LocalDate.of(2022, month, day));

        List<IncomeDetailDto> collect = days.stream()
                .map(m->new IncomeDetailDto(m.getIn_cost(),m.getIn_way(),m.getIn_type(),m.getMemo()))
                .collect(Collectors.toList());

        Map incomeDetails = sortByType(collect);
        return incomeDetails;
    }

    private Map sortByType(List<IncomeDetailDto> collect) {
        List<IncomeDetailDto> salary=new ArrayList<>();
        List<IncomeDetailDto> allowance=new ArrayList<>();
        List<IncomeDetailDto> etc=new ArrayList<>();

        for (IncomeDetailDto detailDto : collect) {
            if (detailDto.getIn_type().equals(money_Type.salary))
                salary.add(detailDto);
            else if (detailDto.getIn_type().equals(money_Type.allowance))
                allowance.add(detailDto);
            else if (detailDto.getIn_type().equals(money_Type.etc))
                etc.add(detailDto);
        }

        Map types = new HashMap();
        types.put("salary", salary);
        types.put("allowance", allowance);
        types.put("etc", etc);

        return types;
    }
}

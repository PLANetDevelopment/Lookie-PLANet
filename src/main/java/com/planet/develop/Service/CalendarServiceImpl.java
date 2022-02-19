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

@Service
@RequiredArgsConstructor
@Log4j2
public class CalendarServiceImpl implements CalendarService {

    private final IncomeService incomeService;
    private final ExpenditureDetailService expenditureDetailService;
    private final UserRepository userRepository;

    /** 1일-31일 동안 하루 지출/수입 */
    @Override
    public CalendarDto findCalendar(String id, int month) {
        Long totalMonthIncome = incomeService.totalMonth(id,month);
        User user = userRepository.findById(id).get();
        Long totalMonthExpenditure = expenditureDetailService.totalMonth(user, month);
        List<CalendarDayDto> calendarDayDtos = new ArrayList<>();


        int days = LocalDate.of(2022,month,month).lengthOfMonth();
        for(int n=1;n<=days;n++) {
            Long incomeDay = incomeService.totalDay(id, LocalDate.of(2022, month,n));
            Long expenditureDay = expenditureDetailService.totalDay(user, LocalDate.of(2022, month, n));
            CalendarDayDto calendarDayDto = new CalendarDayDto(LocalDate.of(2022, month,n), incomeDay, expenditureDay);
            calendarDayDtos.add(calendarDayDto);
        }
        return new CalendarDto(totalMonthIncome,totalMonthExpenditure, calendarDayDtos);
    }

    /** 유형별 하루 지출/수입 상세 */
    public Map<money_Type, List<TypeDetailDto>> findDayExTypeDetail(String id, int month, int day) {
        User user = userRepository.findById(id).get();
        List<Income> in_days = incomeService.findDay(id, LocalDate.of(2022, month, day));
        List<ExpenditureTypeDetailDto> ex_days = expenditureDetailService.findDay(user, LocalDate.of(2022, month, day));

        List<TypeDetailDto> in_detailDtos = getIncomeTypeDtos(in_days);


        List<TypeDetailDto> ex_detailDtos = getExpenditureTypeDtos(ex_days);

        in_detailDtos.addAll(ex_detailDtos);

        Map<money_Type, List<TypeDetailDto>> total = makeListToMap(in_detailDtos);
        return total;
    }

    private List<TypeDetailDto> getIncomeTypeDtos(List<Income> in_days) {
        List<TypeDetailDto> in_detailDtos = new ArrayList<>();
        for (Income dto : in_days) { // 타입 변환
            TypeDetailDto typeDto = new TypeDetailDto();
            typeDto.saveIncomeType(dto.getIn_type(), dto.getIn_cost(), dto.getMemo());
            in_detailDtos.add(typeDto);
        }
        return in_detailDtos;
    }

    private List<TypeDetailDto> getExpenditureTypeDtos(List<ExpenditureTypeDetailDto> ex_days) {
        List<TypeDetailDto> ex_detailDtos = new ArrayList<>();
        for (ExpenditureTypeDetailDto dto : ex_days) { // 타입 변환

            TypeDetailDto typeDto = new TypeDetailDto();
            typeDto.saveExpenditureType(dto.getExType(), dto.getCost(), dto.getMemo(), dto.getEco(), dto.getEcoDetail());
            typeDto.setIncome(false);
            ex_detailDtos.add(typeDto);
        }
        return ex_detailDtos;
    }

    private Map<money_Type, List<TypeDetailDto>> makeListToMap(List<TypeDetailDto> in_detailDtos) {
        Map<money_Type, List<TypeDetailDto>> map = new HashMap<>();
        for (TypeDetailDto dto : in_detailDtos) {
            money_Type key = dto.getType();
            if (map.containsKey(key)) {
                List<TypeDetailDto> list = map.get(key);
                list.add(dto);
            } else {
                List<TypeDetailDto> list = new ArrayList<>();
                list.add(dto);
                map.put(key, list);
            }
        }
        return map;
    }




}
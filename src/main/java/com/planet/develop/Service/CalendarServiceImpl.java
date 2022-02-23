package com.planet.develop.Service;

import com.planet.develop.DTO.*;
import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoDetail;
import com.planet.develop.Enum.EcoEnum;
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

    // TODO
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

    /**
     * 친반환경별 중복 선택 시 List<Map<EcoEnum, EcoDetial>>
     */
    @Override
    public List<EcoDto> dupEcoList(List<ExpenditureTypeDetailDto> ex_days, Long exEno) {
        List<EcoDto> ecoDtoList = new ArrayList<>();
        for (ExpenditureTypeDetailDto dto : ex_days) {
            if (dto.getExEno() == exEno) {
                EcoDto ecoDto = new EcoDto(dto.getEco(), dto.getEcoDetail());
                ecoDtoList.add(ecoDto);
            }
        }
       return ecoDtoList;
    }

    /** 하루 지출 중 expenditure_eno 값을 중복되지 않게 List<Long>에 저장한다. */
    private List<Long> findExEno(List<ExpenditureTypeDetailDto> ex_days) {
        List<Long> dupEcoCheck = new ArrayList<>();
        for (ExpenditureTypeDetailDto dto : ex_days) {
            Long exEno = dto.getExEno();
            if (dupEcoCheck.contains(exEno)) { // exEno가 이미 존재하면 생략
                System.out.println("saved key : " + exEno);
                continue;
            } else {
                dupEcoCheck.add(exEno); // exEno가 존재하지 않는다면 List에 담는다.
                System.out.println("newly saved key : " + exEno);
            }
        }
        return dupEcoCheck;
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
        List<Long> exEnoList = new ArrayList<>();

        Long dupCheck = null;

            for (ExpenditureTypeDetailDto dto : ex_days) { // 타입 변환
                // expenditure_eno에 따라 행이 중복 출력되는 오류 해결
                dupCheck = dto.getExEno();
                if (exEnoList.contains(dupCheck)) // expenditure_eno의 종류만큼 반복한다.
                    continue;
                else
                    exEnoList.add(dupCheck);

                List<EcoDto> ecoList2 = dupEcoList(ex_days, dupCheck);

                TypeDetailDto typeDto = new TypeDetailDto();
                typeDto.saveExpenditureType(dto.getExType(), dto.getCost(), dto.getMemo(), ecoList2);
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
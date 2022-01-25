package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;

import java.time.LocalDate;
import java.util.List;

public interface ExpenditureDetailService {

    Long save(ExpenditureRequestDto dto);

    String totalEcoDay(User user, EcoEnum eco, LocalDate date);

    String totalDay(User user, LocalDate date);

    String totalTypeDay(User user, money_Type type, LocalDate date);

    String totalWayDay(User user, money_Way way, LocalDate date);

    List<Expenditure> getMonthList(User user, int month);

    List<Expenditure> getMonthTypeList(User user, int month, money_Type type);

    List<Expenditure> getMonthWayList(User user, int i, money_Way way);

    List<Expenditure> getMonthEcoList(User user, int month, EcoEnum eco);

    String totalMonth(User user, int month);

    String totalMonthType(User user, int month, money_Type type);

    String totalEcoMonth(User user, int month, EcoEnum eco);

    String totalWayMonth(User user, int month, money_Way way);

    Long update(Long id, ExpenditureRequestDto dto) throws IllegalAccessException;

    /** delete 함수 만들기 */

    default ExpenditureDetail dtoToEntity(ExpenditureRequestDto dto) {
        ExpenditureDetail entity = ExpenditureDetail.builder()
                .ecoDetail(dto.getEcoDetail())
                .eco(dto.getEco())
                .exType(dto.getExType())
                .exWay(dto.getExWay())
                .memo(dto.getMemo())
                .ecoDetail(dto.getEcoDetail())
                .build();
        return entity;
    }

}

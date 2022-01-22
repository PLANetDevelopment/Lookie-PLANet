package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;

import java.time.LocalDate;
import java.util.List;

public interface ExpenditureDetailService {

    Long register(ExpenditureDTO dto);

    String totalDayEco(User user, EcoEnum eco, LocalDate date);

    String totalDay(User user, LocalDate date);

    String totalDayExType(User user, money_Type type, LocalDate date);

    String totalDayExWay(User user, money_Way way, LocalDate date);

    List<Expenditure> findMonthExpenditure(User user, int month);

    List<Expenditure> findMonthExType(User user, int month, money_Type type);

    List<Expenditure> findMonthExWay(User user, int i, money_Way way);

    List<Expenditure> findMonthEco(User user, int month, EcoEnum eco);

    abstract String totalMonth(User user, int month);

    String totalMonthExType(User user, int month, money_Type type);

    String totalMonthEco(User user, int month, EcoEnum eco);

    String totalMonthExWay(User user, int month, money_Way way);

        default ExpenditureDetail dtoToEntity(ExpenditureDTO dto) {
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

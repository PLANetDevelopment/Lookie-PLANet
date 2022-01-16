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

    String totalDayExType(User user, money_Type type, LocalDate date);

    String totalDayExWay(User user, money_Way way, LocalDate date);

    List<Expenditure> findMonthExpenditure(User user, int month);

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

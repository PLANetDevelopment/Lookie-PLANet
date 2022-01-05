package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;

public interface ExpenditureDetailService {

    Long register(ExpenditureDTO dto);

    String totalEco(User user, EcoEnum eco);

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

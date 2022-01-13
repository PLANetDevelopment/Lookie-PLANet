package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.ExpenditureDetail;

public interface ExpenditureDetailService {

    Long register(ExpenditureDTO dto);

    default ExpenditureDetail dtoToEntity(ExpenditureDTO dto) {
        ExpenditureDetail entity = ExpenditureDetail.builder()
                .ecoDetail(dto.getEcoDetail())
                .exType(dto.getExType())
                .exWay(dto.getExWay())
                .memo(dto.getMemo())
                .ecoDetail(dto.getEcoDetail())
                .build();
        return entity;
    }
}

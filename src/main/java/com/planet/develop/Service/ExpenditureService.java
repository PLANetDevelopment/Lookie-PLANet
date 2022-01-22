package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.User;

public interface ExpenditureService {

    Long register(ExpenditureDTO dto, ExpenditureDetail detail);

    default Expenditure dtoToEntity(ExpenditureDTO dto, ExpenditureDetail detail) {

        User user = User.builder()
                .userId(dto.getUserId())
                .build();

//        ExpenditureDetail detail = ExpenditureDetail.builder()
//                .ecoDetail(dto.getEcoDetail())
//                .eco(dto.getEco())
//                .exType(dto.getExType())
//                .exWay(dto.getExWay())
//                .memo(dto.getMemo())
//                .build();

        Expenditure entity = Expenditure.builder()
                .eno(detail.getEno())
                .cost(dto.getCost())
                .user(user)
                .detail(detail)
                .build();

        return entity;
    }
}

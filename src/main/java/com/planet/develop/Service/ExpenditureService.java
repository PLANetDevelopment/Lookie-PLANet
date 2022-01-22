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
        Expenditure entity = Expenditure.builder()
                .eno(detail.getEno())
                .cost(dto.getCost())
                .user(user)
                .detail(detail)
                .build();
        return entity;
    }

    default ExpenditureDTO entityToDto(Expenditure entity) {
        ExpenditureDTO dto = ExpenditureDTO.builder()
                .userId(entity.getUser().getUserId())
                .cost(entity.getCost())
                .date(entity.getDate())
//                .eco(entity.getDetail().getEco())
//                .ecoDetail(entity.getDetail().getEcoDetail())
//                .exType(entity.getDetail().getExType())
//                .exWay(entity.getDetail().getExWay())
//                .memo(entity.getDetail().getMemo())
                .build();
        return dto;
    }

}

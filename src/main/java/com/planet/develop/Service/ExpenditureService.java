package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.User;

public interface ExpenditureService {

    Long save(ExpenditureRequestDto dto, ExpenditureDetail detail);

    default Expenditure dtoToEntity(ExpenditureRequestDto dto, ExpenditureDetail detail) {
        User user = User.builder()
                .userId(dto.getUserId())
                .build();
        Expenditure entity = Expenditure.builder()
                .eno(detail.getEno())
                .cost(dto.getCost())
                .user(user)
                .detail(detail)
                .build();
        entity.changeDate(dto.getDate());
        return entity;
    }

    default ExpenditureRequestDto entityToDto(Expenditure entity) {
        ExpenditureRequestDto dto = ExpenditureRequestDto.builder()
                .userId(entity.getUser().getUserId())
                .cost(entity.getCost())
                .date(entity.getDate())
                .build();
        return dto;
    }

}

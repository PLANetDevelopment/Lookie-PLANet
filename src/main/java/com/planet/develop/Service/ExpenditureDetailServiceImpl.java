package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExpenditureDetailServiceImpl implements ExpenditureDetailService {

    private final ExpenditureDetailRepository repository;

    @Override
    public Long register(ExpenditureDTO dto) {
        ExpenditureDetail entity = dtoToEntity(dto);
        repository.save(entity);
        return entity.getEno();
    }

}

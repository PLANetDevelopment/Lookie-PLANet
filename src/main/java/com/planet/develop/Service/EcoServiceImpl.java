package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.Entity.Eco;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Repository.EcoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EcoServiceImpl implements EcoService {

    @Autowired
    private final EcoRepository repository;

    @Override
    public void save(ExpenditureRequestDto dto, Expenditure expenditure) {
        System.out.println("save started...");
        List<Eco> ecos = dtoToEntity(dto, expenditure);
        for (Eco eco : ecos) {
            repository.save(eco);
        }
    }
}

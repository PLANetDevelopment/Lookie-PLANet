package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.Entity.Eco;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Repository.EcoRepository;
import com.planet.develop.Repository.ExpenditureRepository;
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
    private final EcoRepository ecoRepository;

    @Override
    public void save(ExpenditureRequestDto dto, Expenditure expenditure) {
        System.out.println("save started...");
        List<Eco> ecos = dtoToEntity(dto, expenditure);
        for (Eco eco : ecos) {
            ecoRepository.save(eco);
        }
    }

    @Override
    public void delete(Long id, ExpenditureRequestDto dto) {
        List<Long> ecoEnoList = ecoRepository.getEcoByEno(id);
        for (Long eno : ecoEnoList) // 친/반환경 데이터 전부 삭제하기
            ecoRepository.deleteById(eno);
    }

    @Override
    public void update(Long id, ExpenditureRequestDto dto, Expenditure expenditure) {
        this.delete(id, dto); // 친/반환경 데이터 삭제하기
        this.save(dto, expenditure); // 친/반환경 데이터 다시 저장하기
    }

}

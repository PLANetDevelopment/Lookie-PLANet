package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import com.planet.develop.Repository.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExpenditureDetailServiceImpl implements ExpenditureDetailService {

    private final ExpenditureRepository expenditureRepository;
    private final ExpenditureDetailRepository detailRepository;

    @Override
    public Long register(ExpenditureDTO dto) {
        ExpenditureDetail entity = dtoToEntity(dto);
        detailRepository.save(entity);
        return entity.getEno();
    }

    @Override
    public String totalEco(User user, EcoEnum eco) {
        double total = 0;
        List<Object[]> ecoList = expenditureRepository.getEcoList(user, eco);
        for (Object[] arr : ecoList) {
            double amount = (double) arr[1];
            total += amount;
        }
        return String.format("%.0f", total);
    }

}

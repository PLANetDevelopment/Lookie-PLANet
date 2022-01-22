package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import com.planet.develop.Repository.ExpenditureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UpdateTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ExpenditureService expenditureService;
    @Autowired
    private ExpenditureDetailService detailService;
    @Autowired
    private ExpenditureRepository expenditureRepository;
    @Autowired
    private ExpenditureDetailRepository detailRepository;

    @Test
    public void updateTest() throws IllegalAccessException {
        ExpenditureDTO dto = ExpenditureDTO.builder()
                .cost(1000)
                .exWay(money_Way.card)
                .eco(EcoEnum.G)
                .ecoDetail("2222222")
                .build();
        Long id = detailService.update(1L, dto);
    }

}

package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Way;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTest {

    @Autowired
    private ExpenditureDetailService detailService;

    @Test
    public void updateTest() throws IllegalAccessException {
        ExpenditureRequestDto dto = ExpenditureRequestDto.builder()
                .cost(1000)
                .exWay(money_Way.card)
                .eco(EcoEnum.G)
                .ecoDetail("2222222")
                .build();
        Long id = detailService.update(1L, dto);
    }

}

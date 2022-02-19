package com.planet.develop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class mainResponseDto {
    private String userName;
    private Long totalIncomeMonth;
    private Long totalExpenditureMonth;
}

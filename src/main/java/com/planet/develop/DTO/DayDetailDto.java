package com.planet.develop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayDetailDto {
    List<IncomeDetailDto> in_days;
    List<ExpenditureDetailDto> ex_days;
}

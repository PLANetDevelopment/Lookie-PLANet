package com.planet.develop.DTO;

import com.planet.develop.Enum.money_Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDetailDto {
    boolean isIncome = true;
    money_Type type;
    Long id;
    Long cost;
    String memo;
    List<EcoDto> ecoList;

    public void saveIncomeType(money_Type type, Long cost, String memo,Long id) {
        this.type = type;
        this.cost = cost;
        this.memo = memo;
        this.id=id;
    }

    public void saveExpenditureType(money_Type type, Long cost,
                                    String memo, List<EcoDto> ecoList,Long id) {
        this.type = type;
        this.cost = cost;
        this.memo = memo;
        this.ecoList = ecoList;
        this.id=id;
    }
}
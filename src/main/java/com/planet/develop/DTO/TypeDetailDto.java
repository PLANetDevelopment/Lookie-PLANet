package com.planet.develop.DTO;

import com.planet.develop.Enum.money_Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public void saveIncomeType(money_Type type, Long cost, String memo, Long id) {
        this.type = type;
        this.cost = cost;
        this.memo = memo;
        this.id = id;
    }

    // TODO: 수정
    public void saveExpenditureType(money_Type type, Long id, Long cost,
                                    String memo, List<EcoDto> ecoList) {
        this.type = type;
        this.id = id;
        this.cost = cost;
        this.memo = memo;
        this.ecoList = ecoList;
    }
}

package com.planet.develop.DTO;

import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDetailDto {
    boolean isIncome = true;
    money_Type type;
    Long cost;
    String memo;
    EcoEnum eco;
    String eco_detail;

    public void saveIncomeType(money_Type type, Long cost, String memo) {
        this.type = type;
        this.cost = cost;
        this.memo = memo;
    }

    public void saveExpenditureType(money_Type type, Long cost, String memo,
                                    EcoEnum eco, String eco_detail) {
        this.type = type;
        this.cost = cost;
        this.memo = memo;
        this.eco = eco;
        this.eco_detail = eco_detail;
    }
}

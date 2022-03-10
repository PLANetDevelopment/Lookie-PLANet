package com.planet.develop.DTO;

import com.planet.develop.Enum.EcoDetail;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenditureTypeDetailDto {
    private Long cost; // 비용
    private money_Type exType; // 유형
    private money_Way exWay; // 방법
    private String memo; // 메모
    private EcoEnum eco;
    private EcoDetail ecoDetail;
    private String etcMemo; // 기타메모
    private Long exEno; // expenditure_eno

    public ExpenditureTypeDetailDto(Object cost, Object exType, Object exWay,
                                    Object memo, Object eco, Object ecoDetail,
                                    Object etcMemo, Object exEno) {
        this.cost = (Long) cost;
        this.exType = (money_Type) exType;
        this.exWay = (money_Way) exWay;
        this.memo = (String) memo;
        this.eco = (EcoEnum) eco;
        this.ecoDetail = (EcoDetail) ecoDetail;
        this.etcMemo = (String) etcMemo;
        this.exEno = (Long) exEno;
    }
}
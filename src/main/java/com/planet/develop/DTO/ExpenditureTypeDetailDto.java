package com.planet.develop.DTO;

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
    private EcoEnum eco; // 친/반환경
    private String ecoDetail; // 친/반환경 상세

    public ExpenditureTypeDetailDto(Object cost, Object exType, Object exWay, Object memo, Object eco, Object ecoDetail) {
        this.cost = (Long) cost;
        this.exType = (money_Type) exType;
        this.exWay = (money_Way) exWay;
        this.memo = (String) memo;
        this.eco = (EcoEnum) eco;
        this.ecoDetail = (String) ecoDetail;
    }
}

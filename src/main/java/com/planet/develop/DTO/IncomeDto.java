package com.planet.develop.DTO;

import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IncomeDto {
    private Long id;
    private Long in_cost;
    private money_Way in_way;
    private money_Type in_type;
    private String memo;
    private String date;

    @Builder
    public  IncomeDto(Long in_cost,money_Way in_way,money_Type in_type,String memo,String date){
        this.in_cost=in_cost;
        this.in_way=in_way;
        this.in_type=in_type;
        this.memo=memo;
        this.date=date;
    }

}

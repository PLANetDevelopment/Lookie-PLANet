package com.planet.develop.DTO;

import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Enum.EcoDetail;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenditureRequestDto {
    // TODO: 수정
    private String userId; // 사용자 아이디
    private Long ex_cost; // 비용
    private LocalDate date; // 날짜
    private money_Type exType; // 유형
    private money_Way exWay; // 방법
    private String memo; // 메모
    private List<EcoDetail> ecoDetail; // 친/반환경 상세
    private String etcMemo;
    private EcoEnum eco;

    // TODO: 수정
    public ExpenditureRequestDto(String userId, Long ex_cost, LocalDate date,
                                 money_Type exType, money_Way exWay, String memo,
                                 List<EcoDetail> ecoDetail, String etcMemo) {
        this.userId = userId;
        this.ex_cost = ex_cost;
        this.date = date;
        this.exType = exType;
        this.exWay = exWay;
        this.memo = memo;
        this.ecoDetail = ecoDetail;
        this.etcMemo = etcMemo;
    }
}

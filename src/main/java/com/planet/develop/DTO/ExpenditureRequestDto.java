package com.planet.develop.DTO;

import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenditureRequestDto {
    private String userId; // 사용자 아이디
    private Long cost; // 비용
    private LocalDate date; // 날짜
    private money_Type exType; // 유형
    private money_Way exWay; // 방법
    private String memo; // 메모
    private EcoEnum eco; // 친/반환경
    private String ecoDetail; // 친/반환경 상세
}
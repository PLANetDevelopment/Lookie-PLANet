package com.planet.develop.DTO;

import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenditureDTO {

    private String userId; // 사용자 아이디
    private double cost; // 비용
    private LocalDateTime date; // 날짜
    private money_Type exType; // 유형
    private money_Way exWay; // 방법
    private String memo; // 메모
    private String ecoDetail; // 친/반환경 상세

}

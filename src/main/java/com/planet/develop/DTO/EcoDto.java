package com.planet.develop.DTO;

import com.planet.develop.Enum.EcoDetail;
import com.planet.develop.Enum.EcoEnum;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EcoDto {
    private EcoEnum eco;
    private EcoDetail ecoDetial;
    private String etcMemo;

    public EcoDto(EcoEnum eco, EcoDetail ecoDetial) {
        this.eco = eco;
        this.ecoDetial = ecoDetial;
    }
}

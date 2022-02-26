package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.Entity.Eco;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Enum.EcoDetail;
import com.planet.develop.Enum.EcoEnum;

import java.util.ArrayList;
import java.util.List;

public interface EcoService {

    void save(ExpenditureRequestDto dto, Expenditure expenditure);

    default List<Eco> dtoToEntity(ExpenditureRequestDto dto, Expenditure expenditure) {
        List<Eco> ecoList = new ArrayList<>();
        List<EcoDetail> ecoDetails = dto.getEcoDetail();
        for (EcoDetail ecoDetail : ecoDetails) {
            String etcMemo = null; // etc가 아니라면 null을 유지
            // ecoDetail -> eco(G, N, R)로 변환
            EcoEnum ecoEnum = EcoEnum.N;
            if (EcoDetail.ecoProducts.equals(ecoDetail) || EcoDetail.vegan.equals(ecoDetail)
                    || EcoDetail.multiUse.equals(ecoDetail) || EcoDetail.personalBag.equals(ecoDetail)
                    || EcoDetail.sharing.equals(ecoDetail)) {
                ecoEnum = EcoEnum.G;
            } else if (EcoDetail.disposable.equals(ecoDetail) || EcoDetail.plasticBag.equals(ecoDetail)
                    || EcoDetail.wasteFood.equals(ecoDetail)) {
                ecoEnum = EcoEnum.R;
            } else if (EcoDetail.etc.equals(ecoDetail)) {
                ecoEnum = dto.getEco(); // etc라면 사용자가 지정한 eco 데이터 삽입
                etcMemo = dto.getEtcMemo(); // etc라면 etcMemo 데이터 삽입
            } else {
                ecoEnum = EcoEnum.N;
            }
            Eco entity = Eco.builder()
                    .eco(ecoEnum)
                    .ecoDetail(ecoDetail)
                    .etcMemo(etcMemo)
                    .expenditure(expenditure)
                    .build();
            ecoList.add(entity);
        }
        return ecoList;
    }

}

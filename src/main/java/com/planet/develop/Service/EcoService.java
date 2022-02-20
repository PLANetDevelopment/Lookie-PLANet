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

    // TODO: ecoDetail에 etc가 들어온다면 etcMemo에 값 저장
    // TODO: ecoDetail에 etc가 들어온다면 G, R, N 값 선택

    default List<Eco> dtoToEntity(ExpenditureRequestDto dto, Expenditure expenditure) {
        List<Eco> ecoList = new ArrayList<>();
        List<EcoDetail> ecoDetails = dto.getEcoDetail();
        for (EcoDetail ecoDetail : ecoDetails) {
            // ecoDetail -> eco(G, N, R)로 변환
            EcoEnum ecoEnum = null;
            if (EcoDetail.ecoProducts.equals(ecoDetail) || EcoDetail.vegan.equals(ecoDetail)
                    || EcoDetail.multiUse.equals(ecoDetail) || EcoDetail.personalBag.equals(ecoDetail)
                    || EcoDetail.sharing.equals(ecoDetail)) {
                ecoEnum = EcoEnum.G;
            } else if (EcoDetail.disposable.equals(ecoDetail) || EcoDetail.plasticBag.equals(ecoDetail)
                    || EcoDetail.wasteFood.equals(ecoDetail)) {
                ecoEnum = EcoEnum.R;
            } else if (EcoDetail.etc.equals(ecoDetail)) {
                ecoEnum = EcoEnum.N;
            } else {
                ecoEnum = EcoEnum.N;
            }
            Eco entity = Eco.builder()
                    .eco(ecoEnum)
                    .ecoDetail(ecoDetail)
                    .etcMemo(dto.getEtcMemo())
                    .expenditure(expenditure)
                    .build();
            ecoList.add(entity);
        }
        return ecoList;
    }

}

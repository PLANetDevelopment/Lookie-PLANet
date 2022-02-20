package com.planet.develop.Controller;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.DTO.ExpenditureResponseDto;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import com.planet.develop.Repository.ExpenditureRepository;
import com.planet.develop.Service.EcoService;
import com.planet.develop.Service.ExpenditureDetailService;
import com.planet.develop.Service.ExpenditureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ExpenditureController {

    @Autowired
    ExpenditureDetailRepository detailRepository;
    @Autowired
    ExpenditureRepository expenditureRepository;
    @Autowired
    ExpenditureDetailService detailService;
    @Autowired
    ExpenditureService expenditureService;
    @Autowired
    EcoService ecoService;

    /** 지출 데이터 저장 */
    @PostMapping("/api/expenditure/{id}/new")
    public ExpenditureResponseDto create_expenditure(@PathVariable("id") String id,
                                                @RequestBody ExpenditureRequestDto reuqest) {
        reuqest.setUserId(id);
        Long deno = detailService.save(reuqest); // 지출 상세 테이블 저장
        ExpenditureDetail detail = detailRepository.findById(deno).get(); // 지출 테이블과 매핑
        Long eno = expenditureService.save(reuqest, detail); // 지출 테이블 저장
        Expenditure expenditure = expenditureRepository.findById(eno).get(); // 에코 테이블과 매핑
        ecoService.save(reuqest, expenditure); // 에코 테이블 저장
        return new ExpenditureResponseDto(eno);
    }

    /** 지출 데이터 수정 */
    @PostMapping("/api/expenditure/{id}/update")
    public ExpenditureResponseDto update_expenditure(@PathVariable("id") Long id,
                                                     @RequestBody ExpenditureRequestDto request) throws IllegalAccessException {
        Long eno = detailService.update(id, request);
        return new ExpenditureResponseDto(eno);
    }

    /** 지출 데이터 삭제 */
    @DeleteMapping("/api/expenditure/{id}/delete")
    public void delete_income(@PathVariable("id") Long id){
        detailService.delete(id);
    }

}

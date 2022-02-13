package com.planet.develop.Controller;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.DTO.ExpenditureResponseDto;
import com.planet.develop.DTO.IncomeRequestDto;
import com.planet.develop.DTO.IncomeResponseDto;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Repository.ExpenditureDetailRepository;
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
    ExpenditureDetailService detailService;
    @Autowired
    ExpenditureService expenditureService;

    /** 지출 데이터 저장 */
    @PostMapping("/api/expenditure/{id}/new")
    public ExpenditureResponseDto create_expenditure(@PathVariable("id") String id,
                                                     @RequestBody ExpenditureRequestDto request) {
        request.setUserId(id);
        Long deno = detailService.save(request);
        ExpenditureDetail detail = detailRepository.findById(deno).get();
        Long eno = expenditureService.save(request, detail);
        return new ExpenditureResponseDto(eno);
        // TODO
        // 메인화면으로 redirect
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
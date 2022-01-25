package com.planet.develop.Controller;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.DTO.ExpenditureResponseDto;
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
    public ExpenditureResponseDto create_outcome(@PathVariable("id") String id,
                                                @RequestBody ExpenditureRequestDto reuqest) {
        reuqest.setUserId(id);
        Long deno = detailService.save(reuqest);
        ExpenditureDetail detail = detailRepository.findById(deno).get();
        Long eno = expenditureService.save(reuqest, detail);
        return new ExpenditureResponseDto(eno);
    }

    /** 지출 데이터 삭제 */

    /** 지출 데이터 수정 */

}

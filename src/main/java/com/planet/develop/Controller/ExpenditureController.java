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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@PreAuthorize("permitAll()") // 모든 사용자가 접근 가능
public class ExpenditureController {

    private final ExpenditureDetailRepository detailRepository;
    private final ExpenditureRepository expenditureRepository;
    private final ExpenditureDetailService detailService;
    private final ExpenditureService expenditureService;
    private final EcoService ecoService;

    /** 지출 데이터 저장 */
    @PostMapping("/expenditure/{id}/new")
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

    /** 지출 데이터 수정 전 */
    @GetMapping("/expenditure/{id}/before_update")
    public ExpenditureRequestDto single_expenditure(@PathVariable("id") Long id) throws IllegalAccessException {
        return detailService.getSingleDetail(id);
    }

    /** 지출 데이터 수정 후 */
    @PostMapping("/expenditure/{id}/after_update")
    public ExpenditureResponseDto update_expenditure(@PathVariable("id") Long id, // 여기서 id는 eno를 의미
                                                     @RequestBody ExpenditureRequestDto request) throws IllegalAccessException {
        Long eno = detailService.update(id, request);
        return new ExpenditureResponseDto(eno);
    }

    /** 지출 데이터 삭제 */
    @DeleteMapping("calendar/expenditure/{id}")
    public void delete_income(@PathVariable("id") Long id){
        detailService.delete(id);
    }

}

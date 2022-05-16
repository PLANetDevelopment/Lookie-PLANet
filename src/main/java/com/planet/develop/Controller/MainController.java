package com.planet.develop.Controller;

import com.planet.develop.Entity.User;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Security.DTO.AuthMemberDTO;
import com.planet.develop.Service.ExpenditureDetailService;
import com.planet.develop.Service.IncomeService;
import com.planet.develop.Service.MainService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@PreAuthorize("hasRole('USER')")
public class MainController {
    private final UserRepository userRepository;
    private final IncomeService incomeService;
    private final ExpenditureDetailService expenditureDetailService;
    private final MainService mainService;

    @GetMapping("/main/{id}/{year}/{month}")
    public mainResponseDto main(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @PathVariable("id") String id, @PathVariable("year") int year, @PathVariable("month") int month){
        User user = userRepository.findById(id).get();
        String userName=user.getUserName();
        Long totalMonthIncome = incomeService.totalMonth(user,year,month);
        Long totalMonthExpenditure = expenditureDetailService.totalMonth(user,year, month);
        double ecoPercentage = mainService.getPercentage(user, year, month);
        return new mainResponseDto(authMemberDTO.getEmail(),totalMonthIncome,totalMonthExpenditure,ecoPercentage,100-ecoPercentage);
    }

    @PostMapping("/main/update/{id}/{name}")
    public void mainNameUpdate(@PathVariable("id") String id,@PathVariable("name") String name){
        userRepository.updateName(name,id);
    }

    @Data
    @AllArgsConstructor
    static class mainResponseDto<T>{
        private T userName;
        private T totalIncomeMonth;
        private T totalExpenditureMonth;
        private T ecoPercentage;
        private T noEcoPercentage;
    }

}
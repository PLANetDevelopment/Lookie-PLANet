package com.planet.develop.Controller;

import com.planet.develop.Entity.User;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Service.ExpenditureDetailService;
import com.planet.develop.Service.IncomeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@PreAuthorize("permitAll()") // 모든 사용자가 접근 가능
public class MainController {
    private final UserRepository userRepository;
    private final IncomeService incomeService;
    private final ExpenditureDetailService expenditureDetailService;

    @GetMapping("/main/{id}/{year}/{month}")
    public mainResponseDto main(@PathVariable("id") String id,@PathVariable("year") int year, @PathVariable("month") int month){
        User user = userRepository.findById(id).get();
        String userName=user.getUserName();
        Long totalMonthIncome = incomeService.totalMonth(user,year,month);
        Long totalMonthExpenditure = expenditureDetailService.totalMonth(user,year, month);

        return new mainResponseDto(userName,totalMonthIncome,totalMonthExpenditure);
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
    }

}

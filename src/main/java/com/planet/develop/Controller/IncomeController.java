package com.planet.develop.Controller;

import com.planet.develop.DTO.IncomeRequestDto;
import com.planet.develop.DTO.IncomeResponseDto;
import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Service.IncomeService;
import com.planet.develop.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class IncomeController {
    private final IncomeService incomeService;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/api/income/{id}/new")
    public IncomeResponseDto create_income(@PathVariable("id") String id, @RequestBody IncomeRequestDto request) {
        Optional<User> user = userRepository.findById(id);
        Income income = Income.builder()
                .in_cost(request.getIn_cost())
                .date(request.getDate())
                .in_type(request.getIn_type())
                .in_way(request.getIn_way())
                .memo(request.getMemo())
                .user(user.get())
                .build();
        Long incomeId = incomeService.save(income);
        return new IncomeResponseDto(incomeId);
    }


    @PostMapping("/api/income/{id}/update")
    public IncomeResponseDto update_income(@PathVariable("id") Long id, @RequestBody IncomeRequestDto request){
        incomeService.update(id,request.getIn_cost(),request.getIn_way(),
                request.getIn_type(),request.getMemo(),request.getDate());
        return new IncomeResponseDto(id);
    }

    @DeleteMapping("/api/income/{id}/delete")
    public void delete_income(@PathVariable("id") Long id){
        incomeService.delete(id);
    }

    //TODO
    //조회 함수 어떻게 구현할 지

}
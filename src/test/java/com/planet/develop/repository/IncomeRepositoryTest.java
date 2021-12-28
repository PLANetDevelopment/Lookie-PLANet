package com.planet.develop.repository;

import com.planet.develop.entity.Income;
import com.planet.develop.entity.Mission;
import com.planet.develop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IncomeRepositoryTest {
    @Autowired UserRepository userRepository;
    @Autowired IncomeRepository incomeRepository;
    @Test
    @Transactional
    @Rollback(false)
    public void Income_엔티티_테스트(){
        User user = new User();
        user.setName("memberA");
        userRepository.save(user);

        User findUser = userRepository.find(1L);

        Income income = new Income();
        income.setIn_cost(2000L);
        income.addIncome(findUser);
        incomeRepository.save(income);
    }


}
package com.planet.develop.Service;

import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class IncomeTest {

    Random random = new Random();
    @Autowired
    IncomeService incomeService;

    @Test
    public void 지출_데이터_삽입() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            User user = User.builder()
                    .userId("user" + (int)(Math.random()*10+1) + "@naver.com")
                    .build();
            Income income = Income.builder()
                    .date(LocalDate.of(2022, 01, 23))
                    .memo("income memo")
                    .user(user)
                    .in_cost(Long.valueOf(random.nextInt(100000)))
                    .in_type(money_Type.values()[new Random().nextInt(money_Type.values().length)])
                    .in_way(money_Way.values()[new Random().nextInt(money_Way.values().length)])
                    .build();
            incomeService.save(income);
        });
    }

}

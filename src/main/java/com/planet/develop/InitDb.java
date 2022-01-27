package com.planet.develop;

import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;

/** 테스트 용 user 생성 **/
@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final IncomeService incomeService;
        public void dbInit1() {
            User user1 = User.builder()
                    .userId("yui12@gmail.com")
                    .userName("회원A")
                    .build();

            User user2 = User.builder()
                    .userId("hh2@gmail.com")
                    .userName("회원B")
                    .build();

            User user3 = User.builder()
                    .userId("hdh2@gmail.com")
                    .userName("회원C")
                    .build();

            Income income1 = Income.builder()
                    .in_cost(10000L)
                    .in_type(money_Type.salary)
                    .date(LocalDate.of(2022, 1, 11))
                    .user(user1)
                    .build();
            Income income2 = Income.builder()
                    .in_cost(20000L)
                    .in_type(money_Type.salary)
                    .date(LocalDate.of(2022, 1, 11))
                    .user(user1)
                    .build();
            Income income3 = Income.builder()
                    .in_cost(30000L)
                    .in_type(money_Type.allowance)
                    .date(LocalDate.of(2022, 1, 11))
                    .user(user1)
                    .build();

            em.persist(user1);
            em.persist(user2);
            em.persist(user3);

            em.persist(income1);
            em.persist(income2);
            em.persist(income3);


        }
    }
}
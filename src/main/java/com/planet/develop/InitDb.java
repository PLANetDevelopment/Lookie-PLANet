package com.planet.develop;

import com.planet.develop.Entity.User;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDate;

/** user 예제 생성 **/
@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final IncomeService incomeService;
        public void dbInit1() {
            User user1 = new User("yui12@gmail.com","회원A");
            User user2 = new User("hh2@gmail.com","회원B");
            User user3 = new User("hdh2@gmail.com","회원C");
            em.persist(user1);
            em.persist(user2);
            em.persist(user3);


        }
        public void dbInit2(){
            incomeService.create("yui12@gmail.com",1000L, LocalDate.of(2022, 1, 11), money_Type.salary, money_Way.card,"없음");
            incomeService.create("yui12@gmail.com",2000L,LocalDate.of(2022, 1, 12), money_Type.salary, money_Way.card,"없음");
            incomeService.create("yui12@gmail.com",3000L,LocalDate.of(2022, 1, 13), money_Type.salary, money_Way.card,"없음");


//            incomeService.create("yui12@gmail.com",2000L,LocalDate.of(2022, 1, 11),money_Type.allowance,money_Way.card,"없음");
//            incomeService.create("yui12@gmail.com",3000L,LocalDate.of(2022, 1, 12),money_Type.salary,money_Way.card,"없음");
//            incomeService.create("hh2@gmail.com",3000L,LocalDate.of(2022, 1, 11),money_Type.salary,money_Way.card,"없음");
//            incomeService.create("hh2@gmail.com",3000L,LocalDate.of(2022, 1, 13),money_Type.salary,money_Way.card,"없음");
        }
    }
}

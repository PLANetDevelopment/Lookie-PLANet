package com.planet.develop;

import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.User;
import com.planet.develop.Service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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

            Expenditure expenditure = Expenditure.builder()
                    .cost(10000L)
                    .build();

            em.persist(user1);
            em.persist(user2);
            em.persist(user3);

            em.persist(expenditure);


        }
    }
}
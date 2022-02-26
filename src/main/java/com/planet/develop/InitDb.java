package com.planet.develop;

import com.planet.develop.Entity.*;
import com.planet.develop.Enum.EcoDetail;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
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
                    .in_cost(50000L)
                    .in_type(money_Type.allowance)
                    .date(LocalDate.of(2022, 1, 11))
                    .user(user1)
                    .build();

            Income income4 = Income.builder()
                    .in_cost(30000L)
                    .in_type(money_Type.etc)
                    .date(LocalDate.of(2022, 1, 11))
                    .user(user1)
                    .build();

            Income income5 = Income.builder()
                    .in_cost(30000L)
                    .in_type(money_Type.medical_treatment)
                    .date(LocalDate.of(2022, 1, 11))
                    .user(user1)
                    .build();

            Income income6 = Income.builder()
                    .in_cost(30000L)
                    .in_type(money_Type.culture)
                    .date(LocalDate.of(2022, 1, 11))
                    .user(user1)
                    .build();

             ExpenditureDetail expenditureDetail1 =ExpenditureDetail.builder()
             .exType(money_Type.salary)
             .exWay(money_Way.card)
             .memo("ㅎㅎ")
             .build();



             Expenditure expenditure1 = Expenditure.builder()
             .detail(expenditureDetail1)
             .user(user1)
             .build();
             expenditure1.update(50000L,LocalDate.of(2022, 1, 11));

            Eco eco = Eco.builder()
                    .eco(EcoEnum.G)
                    .ecoDetail(EcoDetail.ecoProducts)
                    .etcMemo("텀블러 사용")
                    .expenditure(expenditure1)
                    .build();

            em.persist(user1);
            em.persist(user2);
            em.persist(user3);

            em.persist(income1);
            em.persist(income2);
            em.persist(income3);
            em.persist(income4);
            em.persist(income5);
            em.persist(income6);

            em.persist(expenditure1);
            em.persist(eco);

        }
    }


}
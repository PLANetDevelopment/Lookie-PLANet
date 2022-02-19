package com.planet.develop;

import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.Mission;
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
        initService.dbInit2();
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
        public void dbInit2() {
            Mission mission1=new Mission();
            mission1.setEmoji("1");
            mission1.setName("잔반 남기지 않기");

            Mission mission2=new Mission();
            mission2.setEmoji("2");
            mission2.setName("냉장고 정리하기");

            Mission mission3=new Mission();
            mission3.setEmoji("3");
            mission3.setName("차량 연료 80%이하로 채우기");

            Mission mission4=new Mission();
            mission4.setEmoji("4");
            mission4.setName("양치컵 사용하기");

            Mission mission5=new Mission();
            mission5.setEmoji("5");
            mission5.setName("샤워시간 단축하기");

            Mission mission6=new Mission();
            mission6.setEmoji("6");
            mission6.setName("불필요한 이메일 삭제하기");

            Mission mission7=new Mission();
            mission7.setEmoji("7");
            mission7.setName("메신저 채팅창 정리하기");

            Mission mission8=new Mission();
            mission8.setEmoji("8");
            mission8.setName("안 쓰는 코드 뽑기");

            Mission mission9=new Mission();
            mission9.setEmoji("9");
            mission9.setName("실내조명 조도 낮추기");

            Mission mission10=new Mission();
            mission10.setEmoji("10");
            mission10.setName("전자기기 사용시간 줄이기");

            Mission mission11=new Mission();
            mission11.setEmoji("11");
            mission11.setName("샤워시간 단축하기");
            Mission mission12=new Mission();
            mission12.setEmoji("12");
            mission12.setName("샤워시간 단축하기");
            Mission mission13=new Mission();
            mission13.setEmoji("13");
            mission13.setName("샤워시간 단축하기");
            Mission mission14=new Mission();
            mission14.setEmoji("14");
            mission14.setName("샤워시간 단축하기");
            Mission mission15=new Mission();
            mission15.setEmoji("15");
            mission15.setName("샤워시간 단축하기");
            Mission mission16=new Mission();
            mission16.setEmoji("16");
            mission16.setName("샤워시간 단축하기");
            Mission mission17=new Mission();
            mission17.setEmoji("17");
            mission17.setName("샤워시간 단축하기");
            Mission mission18=new Mission();
            mission18.setEmoji("18");
            mission18.setName("샤워시간 단축하기");
            Mission mission19=new Mission();
            mission19.setEmoji("19");
            mission19.setName("샤워시간 단축하기");
            Mission mission20=new Mission();
            mission20.setEmoji("20");
            mission20.setName("샤워시간 단축하기");

            em.persist(mission1);
            em.persist(mission2);
            em.persist(mission3);
            em.persist(mission4);
            em.persist(mission5);
            em.persist(mission6);
            em.persist(mission7);
            em.persist(mission8);
            em.persist(mission9);
            em.persist(mission10);
            em.persist(mission11);
            em.persist(mission12);
            em.persist(mission13);
            em.persist(mission14);
            em.persist(mission15);
            em.persist(mission16);
            em.persist(mission17);
            em.persist(mission18);
            em.persist(mission19);
            em.persist(mission20);

        }
    }


}
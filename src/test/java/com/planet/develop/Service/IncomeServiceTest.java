package com.planet.develop.Service;
import com.planet.develop.Entity.Income;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Repository.IncomeRepository;
import com.planet.develop.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class IncomeServiceTest {

    @Autowired
    private EntityManager em;
    @Autowired private IncomeRepository incomeRepository;
    @Autowired private IncomeService incomeService;
    @Autowired private UserRepository userRepository;

    String user_id="yui12@gmail.com";
    LocalDate date=LocalDate.of(2022, 1, 11);

    @Test
    public void cancel_Income_함수_테스트() throws Exception{
        //given
        Long cancel_id=1L;
        //when
        incomeService.delete(cancel_id);
        //then
        Income one = incomeRepository.findOne(cancel_id);
        assertThat(one).isNull();
    }

    @Test
    public void income_일별_조회_테스트() throws Exception{
        //given

        //when
        List<Income> days = incomeService.findDay(user_id,date);
        //then
        assertThat(days.size()).isEqualTo(1);
    }

    @Test
    public void income_type_총합_테스트() throws Exception{
        //given

        //when
        Long total = incomeService.typeDay(user_id,date, money_Type.salary);
        //then
        assertThat(total).isEqualTo(10000);
    }

    @Test
    public void income_way_총합_테스트() throws Exception{
        //given
        String user_id="yui12@gmail.com";
        //when
        Long total = incomeService.wayDay(user_id, LocalDate.of(2022, 1, 11), money_Way.card);
        //then
        System.out.println("total = " + total);
    }

//    @Test
//    public void totalMonth_함수_테스트() throws Exception{
//        //given
//        Long total = incomeService.totalMonth("yui12@gmail.com",1);
//        //then
//        System.out.println("total = " + total);
//
//    }

    @Test
    public void findMonth_함수_테스트() throws Exception{
        //given
        //when
        List<Income> days1=incomeService.findMonth("yui12@gmail.com",1);
        List<Income> days2=incomeService.findMonth("hh2@gmail.com",1);

        //then
        for (Income day : days1) {
            System.out.println(day.getUser().getUserName()+":"+day.getIn_cost());
        }
        System.out.println();
        for (Income day : days2) {
            System.out.println(day.getUser().getUserName()+":"+day.getIn_cost());
        }

    }

    @Test
    public void wayMonth_함수_테스트() throws Exception{
        //given

        //when
        Long total = incomeService.wayMonth("yui12@gmail.com",1, money_Way.card);
        //then
        System.out.println("total = " + total);

    }

    @Test
    public void typeMonth_함수_테스트() throws Exception{
        //given

        //when
        Long total = incomeService.typeMonth("yui12@gmail.com",1, money_Type.salary);
        //then
        System.out.println("total = " + total);

    }

    @Test
    public void update_income_함수_테스트() throws Exception{
        //given

        //when
        incomeService.update(1L,20000L, money_Way.card, money_Type.salary,"업데이트 된 수입",date);
        Income one = incomeRepository.findOne(1L);
        //then
        assertThat(one.getIn_cost()).isEqualTo(20000);

    }


}

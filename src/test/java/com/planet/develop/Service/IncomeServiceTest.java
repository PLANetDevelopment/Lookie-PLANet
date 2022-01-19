package com.planet.develop.Service;

import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Repository.IncomeRepository;
import com.planet.develop.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class IncomeServiceTest {

    @Autowired private  EntityManager em;
    @Autowired private IncomeRepository incomeRepository;
    @Autowired private IncomeService incomeService;
    @Autowired private UserRepository userRepository;


    @Test
    public void income_일별_조회_테스트() throws Exception{
        //given

        //when
        List<Income> days = incomeService.findDay("yui12@gmail.com", LocalDate.of(2022, 1, 11));
        //then
        for (Income day : days) {
            System.out.println("day = " +day.getDate() +" , "+"user = "+day.getUser().getUserName());
        }
    }

    @Test
    public void income_일별_총합_테스트() throws Exception{
        //given

        //when
        Long total = incomeService.totalDay("yui12@gmail.com", LocalDate.of(2022, 1, 11));
        //then
        System.out.println("total = " + total);

    }

    @Test
    public void income_type_총합_테스트() throws Exception{
        //given
        String user_id="yui12@gmail.com";

        //when
        Long total = incomeService.typeDay(user_id, LocalDate.of(2022, 1, 11),money_Type.salary);
        //then
        System.out.println("total = " + total);
    }

    @Test
    public void income_way_총합_테스트() throws Exception{
        //given
        String user_id="yui12@gmail.com";
        //when
        Long total = incomeService.wayDay(user_id, LocalDate.of(2022, 1, 11),money_Way.card);
        //then
        System.out.println("total = " + total);
    }

    @Test
    public void totalMonth_함수_테스트() throws Exception{
        //given
        incomeService.create("yui12@gmail.com",1000L, LocalDate.of(2023, 2, 11), money_Type.salary, money_Way.card,"없음");
        incomeService.create("yui12@gmail.com",2000L,LocalDate.of(2023, 2, 12), money_Type.salary, money_Way.card,"없음");
        incomeService.create("yui12@gmail.com",3000L,LocalDate.of(2023, 2, 13), money_Type.salary, money_Way.card,"없음");
        //when
        Long total = incomeService.totalMonth("yui12@gmail.com",1);
        //then
        System.out.println("total = " + total);
        
    }   
    
    @Test
    public void findMonth_함수_테스트() throws Exception{
        //given
        incomeService.create("yui12@gmail.com",1000L, LocalDate.of(2023, 2, 11), money_Type.salary, money_Way.card,"없음");
        incomeService.create("yui12@gmail.com",2000L,LocalDate.of(2023, 2, 12), money_Type.salary, money_Way.card,"없음");
        incomeService.create("yui12@gmail.com",3000L,LocalDate.of(2023, 2, 13), money_Type.salary, money_Way.card,"없음");
        //when
        List<Income> days=incomeService.findMonth("yui12@gmail.com",1);
        //then
        for (Income day : days) {
            System.out.println(day.getUser().getUserName()+":"+day.getIn_cost());
        }
        
    }

    @Test
    public void wayMonth_함수_테스트() throws Exception{
        //given
        incomeService.create("yui12@gmail.com",1000L, LocalDate.of(2023, 2, 11), money_Type.salary, money_Way.card,"없음");
        incomeService.create("yui12@gmail.com",2000L,LocalDate.of(2023, 2, 12), money_Type.salary, money_Way.card,"없음");
        incomeService.create("yui12@gmail.com",3000L,LocalDate.of(2023, 2, 13), money_Type.salary, money_Way.card,"없음");
        //when
        Long total = incomeService.wayMonth("yui12@gmail.com",1,money_Way.card);
        //then
        System.out.println("total = " + total);

    }

    @Test
    public void typeMonth_함수_테스트() throws Exception{
        //given
        incomeService.create("yui12@gmail.com",1000L, LocalDate.of(2021, 1, 11), money_Type.allowance, money_Way.card,"없음");
        incomeService.create("yui12@gmail.com",2000L,LocalDate.of(2023, 2, 12), money_Type.salary, money_Way.card,"없음");
        incomeService.create("yui12@gmail.com",3000L,LocalDate.of(2023, 2, 13), money_Type.salary, money_Way.card,"없음");
        //when
        Long total = incomeService.typeMonth("yui12@gmail.com",1,money_Type.salary);
        //then
        System.out.println("total = " + total);

    }



}
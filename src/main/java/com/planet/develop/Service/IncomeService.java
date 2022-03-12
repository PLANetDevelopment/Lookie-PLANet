package com.planet.develop.Service;

import com.planet.develop.Entity.Income;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Repository.IncomeRepository;
import com.planet.develop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    /** 수입 입력 **/
    public Long save(Income income) {
        incomeRepository.save(income);
        return income.getId();
    }

    /** 수입 변경 **/
    public void update(Long income_id, Long in_cost, money_Way in_way, money_Type in_type, String memo, LocalDate date){
        Income income = incomeRepository.findOne(income_id);
        income.update_income(in_cost, in_way, in_type, memo, date);
    }

    /** 수입 삭제 **/
    public void delete(Long income_id){
        Income income = incomeRepository.findOne(income_id);
        incomeRepository.delete(income);
    }

    /** 일별 조회 **/
    public List<Income> findDay(String user_id, LocalDate date){
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> days = incomeRepository.findDay(findUser.get(), date);
        return days;
    }

    /** 일별 총합  **/
    public Long totalDay(String user_id,LocalDate date) {
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> days=incomeRepository.findDay(findUser.get(), date);
        Long total=0L;
        for (Income day : days) {
            total+=day.getIn_cost();
        }
        return total;
    }

    /** type 일별 총합 **/
    public Long typeDay(String user_id, LocalDate date, money_Type type) {
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> days=incomeRepository.findDay(findUser.get(),date);
        Long total=0L;
        for (Income day : days) {
            if(day.getIn_type()==type)
                total+=day.getIn_cost();
        }
        return total;
    }

    /** way 일별 총합 **/
    public Long wayDay(String user_id, LocalDate date, money_Way way) {
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> days=incomeRepository.findDay(findUser.get(), date);
        Long total=0L;
        for (Income day : days) {
            if(day.getIn_way()==way)
                total+=day.getIn_cost();
        }
        return total;
    }

    /** 월별 조회 **/
    public List<Income> findMonth(String user_id, int month){
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> days = incomeRepository.findMonth(findUser.get(), month);
        return days;
    }

    /** 월별 총합 **/
    public Long totalMonth(String user_id, int month) {
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> days=incomeRepository.findMonth(findUser.get(),month);
        Long total=0L;
        for (Income day : days) {
            total+=day.getIn_cost();
        }
        return total;
    }

    /** type 월별 총합 **/
    public Long typeMonth(String user_id, int Month, money_Type type) {
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> days=incomeRepository.findMonth(findUser.get(),Month);
        Long total=0L;
        for (Income day : days) {
            if(day.getIn_type()==type)
                total+=day.getIn_cost();
        }
        return total;
    }

    /** way 월별 총합 **/
    public Long wayMonth(String user_id, int Month, money_Way way) {
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> days=incomeRepository.findMonth(findUser.get(), Month);
        Long total=0L;
        for (Income day : days) {
            if(day.getIn_way()==way)
                total+=day.getIn_cost();
        }
        return total;
    }

    /** 한 달 총 수입 **/
    public Long totalMonthDay(String user_id, int month) {
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> incomeList = incomeRepository.findMonthDay(findUser.get(), month);
        Long total=0L;
        for (Income income : incomeList) {
            total += income.getIn_cost();
        }
        return total;
    }

    /** 한 달 특정 일까지의 총 수입 **/
    public Long totalMonthDay(String user_id, int month, int day) {
        Optional<User> findUser = userRepository.findById(user_id);
        List<Income> incomeList = incomeRepository.findMonthDay(findUser.get(), month, day);
        Long total = 0L;
        for (Income income : incomeList) {
            total += income.getIn_cost();
        }
        return total;
    }

}
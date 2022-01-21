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

@Service
@Transactional
@RequiredArgsConstructor
public class IncomeService {
        private final IncomeRepository incomeRepository;
        private final UserRepository userRepository;

        /** 수입 입력 **/
        public Long create(String user_id, Long in_cost, LocalDate date, money_Type in_type, money_Way in_way, String memo) {
                //유저 엔티티 조회
                User user = userRepository.findOne(user_id);
                //수입 엔티티 생성
                Income income = Income.builder()
                        .in_cost(in_cost)
                        .date(date)
                        .in_type(in_type)
                        .in_way(in_way)
                        .memo(memo)
                        .user(user)
                        .build();
                //저장
                incomeRepository.save(income);
                return income.getId();
        }

        /** 수입 삭제 **/
        public void cancel(Long income_id){
                Income income = incomeRepository.findOne(income_id);
                incomeRepository.delete(income);
        }

        /** 수입 변경 **/
        public void update(Long income_id, Long in_cost, LocalDate date, money_Type in_type, money_Way in_way, String memo){
               Income income = incomeRepository.findOne(income_id);
               income.update_income(in_cost,in_way,in_type,memo,date);
        }

        /** 일별 조회 **/
        public List<Income> findDay(String user_id,LocalDate date){
                User findUser = userRepository.findOne(user_id);
                List<Income> days = incomeRepository.findDay(findUser, date);
                return days;
        }

        /** 일별 총합  **/
        public Long totalDay(String user_id,LocalDate date) {
                User findUser = userRepository.findOne(user_id);
                List<Income> days=incomeRepository.findDay(findUser, date);
                Long total=0L;
                for (Income day : days) {
                        total+=day.getIn_cost();
                }
                return total;
        }
        /** type 일별 총합 **/
        public Long typeDay(String user_id,LocalDate date,money_Type type) {
                User findUser = userRepository.findOne(user_id);
                List<Income> days=incomeRepository.findDay(findUser,date);
                Long total=0L;
                for (Income day : days) {
                        if(day.getIn_type()==type)
                                total+=day.getIn_cost();
                }
                return total;
        }

        /** way 일별 총합 **/
        public Long wayDay(String user_id,LocalDate date,money_Way way) {
                User findUser = userRepository.findOne(user_id);
                List<Income> days=incomeRepository.findDay(findUser, date);
                Long total=0L;
                for (Income day : days) {
                        if(day.getIn_way()==way)
                                total+=day.getIn_cost();
                }
                return total;
        }

        /** 월별 조회 **/
        public List<Income> findMonth(String user_id,int month){
                User findUser = userRepository.findOne(user_id);
                List<Income> days = incomeRepository.findMonth(findUser, month);
                return days;
        }
        
        /** 월별 총합 **/
        public Long totalMonth(String user_id,int month) {
                User findUser = userRepository.findOne(user_id);
                List<Income> days=incomeRepository.findMonth(findUser,month);
                Long total=0L;
                for (Income day : days) {
                        total+=day.getIn_cost();
                }
                return total;

        }

        /** type 월별 총합 **/
        public Long typeMonth(String user_id,int Month,money_Type type) {
                User findUser = userRepository.findOne(user_id);
                List<Income> days=incomeRepository.findMonth(findUser,Month);
                Long total=0L;
                for (Income day : days) {
                        if(day.getIn_type()==type)
                                total+=day.getIn_cost();
                }
                return total;
        }

        /** way 월별 총합 **/
        public Long wayMonth(String user_id,int Month,money_Way way) {
                User findUser = userRepository.findOne(user_id);
                List<Income> days=incomeRepository.findMonth(findUser, Month);
                Long total=0L;
                for (Income day : days) {
                        if(day.getIn_way()==way)
                                total+=day.getIn_cost();
                }
                return total;
        }

}

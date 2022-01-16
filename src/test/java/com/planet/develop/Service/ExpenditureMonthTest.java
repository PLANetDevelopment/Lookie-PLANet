package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.User;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import com.planet.develop.Repository.ExpenditureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ExpenditureMonthTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ExpenditureService expenditureService;
    @Autowired
    private ExpenditureDetailService detailService;
    @Autowired
    private ExpenditureRepository expenditureRepository;
    @Autowired
    private ExpenditureDetailRepository detailRepository;

    /**
     * 사용자 아이디: user1@naver.com
     * 한 달 사용 내역
     */
    @Test
    public void 사용자의_한달_사용내역_구하기() {
        User user = User.builder()
                .userId("user1@naver.com")
                .build();
        List<Expenditure> monthList = detailService.findMonthExpenditure(user, 1);
        System.out.println("------지출 리스트------");
        for (Expenditure e : monthList) {
            ExpenditureDTO expenditureDTO = expenditureService.entityToDto(e);
            System.out.println("userID: " + expenditureDTO.getUserId() + " cost: "
                    + expenditureDTO.getCost() + " date: " + expenditureDTO.getDate());
        }
    }

}

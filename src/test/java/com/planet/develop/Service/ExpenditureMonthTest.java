package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
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

    /**
     * 사용자 아이디: user1@naver.com
     * 지출 유형: study
     */
    @Test
    public void 사용자의_한달_지출유형별_사용내역_구하기() {
        User user = User.builder()
                .userId("user1@naver.com")
                .build();
        money_Type type = money_Type.study;
        List<Expenditure> monthList = detailService.totalMonthExType(user, 1, type);
        System.out.println("------지출 리스트------");
        for (Expenditure e : monthList) {
            ExpenditureDTO expenditureDTO = expenditureService.entityToDto(e);
            System.out.println("userID: " + expenditureDTO.getUserId() + " cost: "
                    + expenditureDTO.getCost() + " date: " + expenditureDTO.getDate() + " exType: " + type);
        }
    }

    /**
     * 사용자 아이디: user1@naver.com
     * 지출 방법: card
     */
    @Test
    public void 사용자의_한달_지출방법별_사용내역_구하기() {
        User user = User.builder()
                .userId("user1@naver.com")
                .build();
        money_Way way = money_Way.card;
        List<Expenditure> monthList = detailService.totalMonthExWay(user, 1, way);
        System.out.println("------지출 리스트------");
        for (Expenditure e : monthList) {
            ExpenditureDTO expenditureDTO = expenditureService.entityToDto(e);
            System.out.println("userID: " + expenditureDTO.getUserId() + " cost: "
                    + expenditureDTO.getCost() + " date: " + expenditureDTO.getDate() + " exWay: " + way);
        }
    }

    /**
     * 사용자 아이디: user1@naver.com
     * 친/반환경: G
     */
    @Test
    public void 사용자의_한달_친반환경별_사용내역_구하기() {
        User user = User.builder()
                .userId("user1@naver.com")
                .build();
        EcoEnum eco = EcoEnum.G;
        List<Expenditure> monthList = detailService.totalMonthEco(user, 1, eco);
        System.out.println("------지출 리스트------");
        for (Expenditure e : monthList) {
            ExpenditureDTO expenditureDTO = expenditureService.entityToDto(e);
            System.out.println("userID: " + expenditureDTO.getUserId() + " cost: "
                    + expenditureDTO.getCost() + " date: " + expenditureDTO.getDate() + " eco: " + eco);
        }
    }

}

package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
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

    int month = 1;

    /**
     * 사용자 아이디: user1@naver.com
     * 한 달 사용 내역
     */
    @Test
    public void 사용자의_한달_사용내역_구하기() {
        User user = User.builder()
                .userId("user1@naver.com")
                .build();
        List<Expenditure> monthList = detailService.getMonthList(user, 1);
        System.out.println("------지출 리스트------");
        for (Expenditure e : monthList) {
            ExpenditureRequestDto ExpenditureRequestDto = expenditureService.entityToDto(e);
            System.out.println("userID: " + ExpenditureRequestDto.getUserId() + " cost: "
                    + ExpenditureRequestDto.getCost() + " date: " + ExpenditureRequestDto.getDate());
        }
        System.out.println(user.getUserId() + " 님의 " + month + "월 "
                + "총 지출 금액은 " + detailService.totalMonth(user, month) + "원 입니다.");
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
        List<Expenditure> monthList = detailService.getMonthTypeList(user, month, type);
        System.out.println("------지출 리스트------");
        for (Expenditure e : monthList) {
            ExpenditureRequestDto ExpenditureRequestDto = expenditureService.entityToDto(e);
            System.out.println("userID: " + ExpenditureRequestDto.getUserId() + " cost: "
                    + ExpenditureRequestDto.getCost() + " date: " + ExpenditureRequestDto.getDate() + " exType: " + type);
        }
        System.out.println(user.getUserId() + " 님의 " + month + "월 " + type + "별"
                + " 총 지출 금액은 " + detailService.totalMonthType(user, month, type) + "원 입니다.");
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
        List<Expenditure> monthList = detailService.getMonthWayList(user, month, way);
        System.out.println("------지출 리스트------");
        for (Expenditure e : monthList) {
            ExpenditureRequestDto ExpenditureRequestDto = expenditureService.entityToDto(e);
            System.out.println("userID: " + ExpenditureRequestDto.getUserId() + " cost: "
                    + ExpenditureRequestDto.getCost() + " date: " + ExpenditureRequestDto.getDate() + " exWay: " + way);
        }
        System.out.println(user.getUserId() + " 님의 " + month + "월 " + way + "별"
                + " 총 지출 금액은 " + detailService.totalWayMonth(user, month, way) + "원 입니다.");
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
        List<Expenditure> monthList = detailService.getMonthEcoList(user, month, eco);
        System.out.println("------지출 리스트------");
        for (Expenditure e : monthList) {
            ExpenditureRequestDto ExpenditureRequestDto = expenditureService.entityToDto(e);
            System.out.println("userID: " + ExpenditureRequestDto.getUserId() + " cost: "
                    + ExpenditureRequestDto.getCost() + " date: " + ExpenditureRequestDto.getDate() + " eco: " + eco);
        }
        System.out.println(user.getUserId() + " 님의 " + month + "월 " + eco + "별"
                + " 총 지출 금액은 " + detailService.totalEcoMonth(user, month, eco) + "원 입니다.");
    }
    
}

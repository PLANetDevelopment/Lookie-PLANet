package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.DTO.UserDTO;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import com.planet.develop.Repository.ExpenditureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class ExpenditureTest {

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

    Random random = new Random();

    @Test
    public void 회원가입() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            UserDTO userDTO = UserDTO.builder()
                    .userId("user" + i + "@naver.com")
                    .userName("user" + i)
                    .build();
            userService.register(userDTO);
        });
    }

    /**
     * ExpenditureDTO (지출 dto)를
     * Expenditure과 ExpenditureDetail Entity로 변환하여
     * DB에 저장
     */
    @Test
    public void 지출_데이터_삽입() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            ExpenditureRequestDto dto = ExpenditureRequestDto.builder()
                    .cost(Long.valueOf(random.nextInt(100000))) // 가격 랜덤 삽입
                    .eco(EcoEnum.values()[new Random().nextInt(EcoEnum.values().length)]) // 친/반환경 랜덤 삽입
                    .ecoDetail("good detail")
                    .exType(money_Type.values()[new Random().nextInt(money_Type.values().length)]) // 유형 랜덤 삽입
                    .exWay(money_Way.values()[new Random().nextInt(money_Way.values().length)]) // 방법 랜덤 삽입
                    .memo("good memo")
                    .date(LocalDate.of(2022, 01, 23))
                    .userId("user" + (int)(Math.random()*10+1) + "@naver.com") // 사용자 아이디 랜덤 삽입
                    .build();
            Long deno = detailService.save(dto);
            System.out.println("expenditrue detail table: " + deno);
            ExpenditureDetail detail = detailRepository.findById(deno).get();
            Long eno = expenditureService.save(dto, detail);
            System.out.println("expenditure table: " + eno);
        });
    }

    /**
     * 사용자 아이디: user10@naver.com
     * 친반환경: G
     */
    @Test
    public void 사용자별_하루_친반환경_지출내역_가져오기() {
        User user = User.builder()
                .userId("user10@naver.com")
                .build();
        EcoEnum eco = EcoEnum.G;
        LocalDate date = LocalDate.now();
        List<Object[]> ecoList = expenditureRepository.getDayEcoList(user, eco, date);
        System.out.println("-----지출 리스트-----");
        for (Object[] arr : ecoList)
            System.out.println(Arrays.toString(arr));
        System.out.println(user.getUserId() + " 님의 " + date + " " + eco + " 별"
                + " 총 지출 금액은 " + detailService.totalEcoDay(user, eco, date) + "원 입니다.");
    }

    /**
     * 사용자 아이디: user1@naver.com
     * 지출 유형: study
     */
    @Test
    public void 사용자별_하루_카테고리별_지출내역_가져오기() {
        User user = User.builder()
                .userId("user1@naver.com")
                .build();
        money_Type type = money_Type.study;
        LocalDate date = LocalDate.now();
        List<Object[]> ecoList = expenditureRepository.getDayExTypeList(user, type, date);
        System.out.println("-----지출 리스트-----");
        for (Object[] arr : ecoList)
            System.out.println(Arrays.toString(arr));
        System.out.println(user.getUserId() + " 님의 " + date + " " + type + "별"
                + " 총 지출 금액은 " + detailService.totalTypeDay(user, type, date) + "원 입니다.");
    }

    /**
     * 사용자 아이디: user1@naver.com
     * 지출 방법: card
     */
    @Test
    public void 사용자별_하루_지출방법별_지출내역_가져오기() {
        User user = User.builder()
                .userId("user1@naver.com")
                .build();
        money_Way way = money_Way.card;
        LocalDate date = LocalDate.now();
        List<Object[]> ecoList = expenditureRepository.getDayExWayList(user, way, date);
        System.out.println("---------지출 리스트----------");
        for (Object[] arr : ecoList)
            System.out.println(Arrays.toString(arr));
        System.out.println(user.getUserId() + " 님의 " + date + " " + way + "별"
                + " 총 지출 금액은 " + detailService.totalWayDay(user, way, date) + "원 입니다.");
    }

}

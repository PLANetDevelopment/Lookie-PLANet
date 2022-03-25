package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.DTO.UserDTO;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Enum.EcoDetail;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import com.planet.develop.Repository.ExpenditureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Autowired
    private EcoService ecoService;

    Random random = new Random();

    @Test
    public void 회원가입() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            UserDTO userDTO = UserDTO.builder()
                    .userId("user" + i + "@naver.com")
                    .userName("user" + i)
                    .build();
            userService.register(userDTO);
        });
    }

    @Test
    public void 지출_데이터_삽입() {
        List<Long> costList = new ArrayList<>(); // 지출 금액 리스트
        costList.add(1000L);
        costList.add(1000L);
        costList.add(5300L);
        costList.add(14300L);
        costList.add(1500L);
        costList.add(25000L);
        costList.add(2000L);
        costList.add(3800L);
        costList.add(20000L);

        List<LocalDate> dateList = new ArrayList<>(); // 날짜 리스트트
        dateList.add(LocalDate.of(2022, 02, 14));
        dateList.add(LocalDate.of(2022, 02, 23));
       dateList.add(LocalDate.of(2022, 01, 27));
       dateList.add(LocalDate.of(2022, 01, 15));
       dateList.add(LocalDate.of(2022, 03, 2));
       dateList.add(LocalDate.of(2022, 03, 16));
       dateList.add(LocalDate.of(2022, 03, 21));
       dateList.add(LocalDate.of(2022, 03, 23));
       dateList.add(LocalDate.of(2022, 03, 4));

        List<EcoDetail> eco = new ArrayList<>();
        eco.add(EcoDetail.sharing);
        List<EcoDetail> neco = new ArrayList<>();
        neco.add(EcoDetail.wasteFood);
        List<EcoDetail> ecoNeco = new ArrayList<>();
        ecoNeco.add(EcoDetail.wasteFood);
        ecoNeco.add(EcoDetail.sharing);
        List<EcoDetail> ecoecoNeco = new ArrayList<>();
        ecoecoNeco.add(EcoDetail.sharing);
        ecoecoNeco.add(EcoDetail.vegan);
        ecoecoNeco.add(EcoDetail.wasteFood);
        List<EcoDetail> ecoNecoNeco = new ArrayList<>();
        ecoNecoNeco.add(EcoDetail.sharing);
        ecoNecoNeco.add(EcoDetail.wasteFood);
        ecoNecoNeco.add(EcoDetail.wasteFood);

        List<List<EcoDetail>> ecoList = new ArrayList<>();
        ecoList.add(neco);
        ecoList.add(ecoNeco);
        ecoList.add(ecoNeco);
        ecoList.add(ecoecoNeco);
        ecoList.add(eco);
        ecoList.add(ecoNeco);
        ecoList.add(eco);
        ecoList.add(neco);
        ecoList.add(ecoNecoNeco);

        List<String> memoList = new ArrayList<>();
        memoList.add("빵 사먹음");
        memoList.add("택시 탐");
        memoList.add("동료한테 커피 사줌");
        memoList.add(null);
        memoList.add("친구랑 밥 먹음");
        memoList.add("쇼핑함");
        memoList.add("지하철 탐");
        memoList.add("예쁜 쓰레기 삼");
        memoList.add("마카롱 삼삼");

       IntStream.rangeClosed(0, 6).forEach(i -> {
            ExpenditureRequestDto dto = ExpenditureRequestDto.builder()
                    .ex_cost(Long.valueOf(random.nextInt(100) * 1000)) // 가격 랜덤 삽입
//                    .ex_cost(costList.get(i))
                    .ecoDetail(ecoList.get(i))
                    .etcMemo(null)
                    .eco(null)
                    .exType(money_Type.values()[new Random().nextInt(money_Type.values().length)]) // 유형 랜덤 삽입
                    .exWay(money_Way.values()[new Random().nextInt(money_Way.values().length)]) // 방법 랜덤 삽입
                    .memo(memoList.get(i))
                    .date(dateList.get(i))
//                    .userId("user" + (int)(Math.random()*3+1) + "@naver.com") // 사용자 아이디 랜덤 삽입
                    .userId("user1@naver.com") // 사용자 아이디 랜덤 삽입
                    .build();

            Long deno = detailService.save(dto);
            ExpenditureDetail detail = detailRepository.findById(deno).get();
            Long eno = expenditureService.save(dto, detail);
            Expenditure expenditure = expenditureRepository.findById(eno).get();
            ecoService.save(dto, expenditure);
        });
    }

//    /**
//     * 사용자 아이디: user10@naver.com
//     * 친반환경: G
//     */
//    @Test
//    public void 사용자별_하루_친반환경_지출내역_가져오기() {
//        User user = User.builder()
//                .userId("user10@naver.com")
//                .build();
//        EcoEnum eco = EcoEnum.G;
//        LocalDate date = LocalDate.now();
//        List<Object[]> ecoList = expenditureRepository.getDayEcoList(user, eco, date);
//        System.out.println("-----지출 리스트-----");
//        for (Object[] arr : ecoList)
//            System.out.println(Arrays.toString(arr));
//        System.out.println(user.getUserId() + " 님의 " + date + " " + eco + " 별"
//                + " 총 지출 금액은 " + detailService.totalEcoDay(user, eco, date) + "L입니다.");
//    }
//
//    /**
//     * 사용자 아이디: user1@naver.com
//     * 지출 유형: study
//     */
//    @Test
//    public void 사용자별_하루_카테고리별_지출내역_가져오기() {
//        User user = User.builder()
//                .userId("user1@naver.com")
//                .build();
//        money_Type type = money_Type.study;
//        LocalDate date = LocalDate.now();
//        List<Object[]> ecoList = expenditureRepository.getDayExTypeList(user, type, date);
//        System.out.println("-----지출 리스트-----");
//        for (Object[] arr : ecoList)
//            System.out.println(Arrays.toString(arr));
//        System.out.println(user.getUserId() + " 님의 " + date + " " + type + "별"
//                + " 총 지출 금액은 " + detailService.totalTypeDay(user, type, date) + "L입니다.");
//    }
//
//    /**
//     * 사용자 아이디: user1@naver.com
//     * 지출 방법: card
//     */
//    @Test
//    public void 사용자별_하루_지출방법별_지출내역_가져오기() {
//        User user = User.builder()
//                .userId("user1@naver.com")
//                .build();
//        money_Way way = money_Way.card;
//        LocalDate date = LocalDate.now();
//        List<Object[]> ecoList = expenditureRepository.getDayExWayList(user, way, date);
//        System.out.println("---------지출 리스트----------");
//        for (Object[] arr : ecoList)
//            System.out.println(Arrays.toString(arr));
//        System.out.println(user.getUserId() + " 님의 " + date + " " + way + "별"
//                + " 총 지출 금액은 " + detailService.totalWayDay(user, way, date) + "L입니다.");
//    }

}

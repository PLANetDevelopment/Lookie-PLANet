package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDTO;
import com.planet.develop.DTO.UserDTO;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import com.planet.develop.Repository.ExpenditureRepository;
import com.planet.develop.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    private UserRepository userRepository;
    @Autowired
    private ExpenditureRepository expenditureRepository;
    @Autowired
    private ExpenditureDetailRepository detailRepository;

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
     * DB에 저장 (총 100개의 데이터 삽입)
     */
    @Test
    public void 수입_데이터_삽입() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            ExpenditureDTO dto = ExpenditureDTO.builder()
                    .cost((int)((Math.random()*1000+1)*100)) // 가격 랜덤 삽입
                    .eco(EcoEnum.values()[new Random().nextInt(EcoEnum.values().length)]) // 친/반환경 랜덤 삽입
                    .ecoDetail("good detail")
                    .exType(money_Type.values()[new Random().nextInt(money_Type.values().length)]) // 유형 랜덤 삽입
                    .exWay(money_Way.values()[new Random().nextInt(money_Way.values().length)]) // 방법 랜덤 삽입
                    .memo("good memo")
                    .userId("user" + (int)(Math.random()*10+1) + "@naver.com") // 사용자 아이디 랜덤 삽입
                    .build();
            Long deno = detailService.register(dto);
            System.out.println("expenditrue detail table: " + deno);
            ExpenditureDetail detail = detailRepository.findById(deno).get();
            Long eno = expenditureService.register(dto, detail);
            System.out.println("expenditure table: " + eno);
        });
    }

}

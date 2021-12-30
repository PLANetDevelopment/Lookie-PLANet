package com.planet.develop.Service;

import com.planet.develop.DTO.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ExpenditureTest {

    @Autowired
    private UserService userService;

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

}

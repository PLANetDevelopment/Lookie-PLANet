package com.planet.develop.Service;

import com.planet.develop.DTO.UserDTO;
import com.planet.develop.Entity.Grade;
import com.planet.develop.Entity.User;

public interface UserService {

    /**
     * 이미 회원가입 된 아이디인지 확인
     * 새로운 아이디라면 db에 저장 (point와 grade는 0으로 초기화)
     * @return 사용자 아이디
     */
    String register(UserDTO dto);

    default User dtoToEntity(UserDTO dto) {

        Grade grade = Grade.builder()
                .userId(dto.getUserId())
                .grade("0")
                .build();

        User entity = User.builder()
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .point(0)
                .grade(grade)
                .build();

        return entity;
    }

}

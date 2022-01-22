package com.planet.develop.Service;

import com.planet.develop.DTO.UserDTO;
import com.planet.develop.Entity.User;

public interface UserService {

    /**
     * 이미 회원가입 된 아이디인지 확인
     * 새로운 아이디라면 db에 저장
     * @return 사용자 아이디
     */
    String register(UserDTO dto);

    default User dtoToEntity(UserDTO dto) {

        User entity = User.builder()
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .point(0)
                .build();

        return entity;
    }

}

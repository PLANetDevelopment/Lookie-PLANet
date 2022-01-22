package com.planet.develop.DTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * 사용자 테이블과 등급 테이블에 접근할 DTO
 */
public class UserDTO {

    private String userId;
    private String userName;

}

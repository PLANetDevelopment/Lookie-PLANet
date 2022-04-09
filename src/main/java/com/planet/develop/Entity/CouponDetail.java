package com.planet.develop.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    private String couponNum; // 쿠폰 번호

    private LocalDate startDate; // 시작 날짜

    private String usageInfo; // 사용 정보

    private String couponInfo; // 쿠폰 정보

    private String detailInfo; // 상세 정보
}

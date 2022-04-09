package com.planet.develop.Entity;

import com.planet.develop.DTO.CouponDto;
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
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    private String coupon; // 쿠폰명
    private String image; // 쿠폰 이미지
    private int discount; // 할인율
    private LocalDate endDate; // 종료 날짜
    private int remainigDays; // 남은 날짜
    private boolean availability; // 사용 가능 여부
    private boolean expiration; // 만료 여부

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "cno")
    private CouponDetail couponDetail; // 쿠폰 상세 테이블과 일대일 매핑

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 사용자 테이블과 일대다 매핑

    public CouponDto entityToDto() {
        CouponDto couponDto = new CouponDto(
                coupon, discount, image, remainigDays, availability, expiration);
        return couponDto;
    }
}

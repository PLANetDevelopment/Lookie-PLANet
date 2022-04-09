package com.planet.develop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {
    int count; // 쿠폰 개수
    String coupon; // 쿠폰 이름
    int discount; // 할인율
    String image; // 쿠폰 이미지
    int remainingDays; // 남은 날짜
    boolean availability; // 사용 가능 여부
    boolean expiration; // 만료 여부

    public CouponDto(String coupon, int discount, String image,
                     int remainingDays, boolean availability, boolean expiration) {
        this.coupon = coupon;
        this.discount = discount;
        this.image = image;
        this.remainingDays = remainingDays;
        this.availability = availability;
        this.expiration = expiration;
    }
}

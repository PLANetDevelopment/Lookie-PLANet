package com.planet.develop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {
    String cno; // 쿠폰 번호
    String coupon; // 쿠폰 이름
    int remainingDays; // 남은 날짜
    int discount; // 할인율
    boolean availability; // 사용 가능 여부
    boolean expiration; // 만료 여부

    public CouponDto(Object cno, Object coupon, Object remainingDays, Object discount,
                     Object availability, Object expiration) {
        this.cno = (String) cno;
        this.coupon = (String) coupon;
        this.remainingDays = (int) remainingDays;
        this.discount = (int) discount;
        this.availability = (boolean) availability;
        this.expiration = (boolean) expiration;
    }
}

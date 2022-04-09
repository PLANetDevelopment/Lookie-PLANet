package com.planet.develop.Service;

import com.planet.develop.Entity.Coupon;

import java.util.List;

public interface CouponService {
    /** 사용자 쿠폰 조회 */
    List<Coupon> getCouponList(String id);
}

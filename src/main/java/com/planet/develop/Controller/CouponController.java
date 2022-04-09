package com.planet.develop.Controller;

import com.planet.develop.DTO.CouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@PreAuthorize("permitAll()") // 모든 사용자가 접근 가능
public class CouponController {

    /** 쿠폰 조회 페이지 */
    @GetMapping("{id}/mypage/coupon")
    public CouponDto findCoupon(@PathVariable("id") String id) {
        CouponDto couponDto = new CouponDto();
        return couponDto;
    }

}

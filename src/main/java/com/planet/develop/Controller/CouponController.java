package com.planet.develop.Controller;

import com.planet.develop.DTO.CouponDetailDto;
import com.planet.develop.DTO.CouponListDto;
import com.planet.develop.Service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponService couponService;

    /** 쿠폰 리스트 조회 페이지 */
    @GetMapping("{id}/mypage/coupon")
    public CouponListDto findCoupon(@PathVariable("id") String id) {
        System.out.println("Controller started...");
        CouponListDto couponList = couponService.getCouponList(id);
        return couponList;
    }

    /** 쿠폰 상세 조회 페이지 */
    @GetMapping("/mypage/coupon/{cno}/detail")
    public CouponDetailDto findCouponDetail (@PathVariable("cno") String cno) {
        CouponDetailDto detailDto = couponService.getCouponDetail(cno);
        return detailDto;
    }

    /** 쿠폰 등록 페이지 */
    @PostMapping("/mypage/coupon/{id}/register")
    public String couponRegister(@PathVariable("id") String id, @RequestParam("cno") String cno) {
        couponService.couponRegister(id, cno);
        return cno;
    }

    /** 쿠폰 사용 */
    @PostMapping("/mypage/coupon/{id}/use")
    public String useCoupon(@PathVariable("id") String id, @RequestParam("cno") String cno) throws IllegalAccessException {
        couponService.useCoupon(id, cno);
        return "쿠폰 사용 완료";
    }

}

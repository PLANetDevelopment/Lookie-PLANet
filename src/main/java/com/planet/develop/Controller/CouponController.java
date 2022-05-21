package com.planet.develop.Controller;

import com.planet.develop.DTO.CouponDetailDto;
import com.planet.develop.DTO.CouponListDto;
import com.planet.develop.Entity.User;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Security.DTO.AuthMemberDTO;
import com.planet.develop.Service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponService couponService;
    private final UserRepository userRepository;

    /** 쿠폰 리스트 조회 페이지 */
    @GetMapping("/mypage/coupon")
    public CouponListDto findCoupon(@AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        User user = userRepository.findById(authMemberDTO.getEmail()).get();
        CouponListDto couponList = couponService.getCouponList(user.getUserId());
        return couponList;
    }

    /** 쿠폰 상세 조회 페이지 */
    @GetMapping("/mypage/coupon/{cno}/detail")
    public CouponDetailDto findCouponDetail (@PathVariable("cno") String cno) {
        CouponDetailDto detailDto = couponService.getCouponDetail(cno);
        return detailDto;
    }

    /** 쿠폰 등록 페이지 */
    @PostMapping("/mypage/coupon/register")
    public String couponRegister(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @RequestParam("cno") String cno) {
        User user = userRepository.findById(authMemberDTO.getEmail()).get();
        couponService.couponRegister(user.getUserId(), cno);
        return cno;
    }

    /** 쿠폰 사용 */
    @PostMapping("/mypage/coupon/{id}/use")
    public String useCoupon(@RequestParam("cno") String cno) throws IllegalAccessException {
        couponService.useCoupon(cno);
        return "쿠폰 사용 완료";
    }

}
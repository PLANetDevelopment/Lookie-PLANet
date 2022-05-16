//package com.planet.develop.Service;
//
//import com.planet.develop.Entity.Coupon;
//import com.planet.develop.Entity.CouponDetail;
//import com.planet.develop.Entity.User;
//import com.planet.develop.Repository.CouponDetailRepository;
//import com.planet.develop.Repository.CouponRepository;
//import com.planet.develop.Repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//
//@SpringBootTest
//public class CouponTest {
//
//    UserRepository userRepository;
//    CouponRepository couponRepository;
//    CouponDetailRepository couponDetailRepository;
//
//    @Test
//    public void insertDummmies() {
//        User user = userRepository.findById("zinzo1019@gmail.com").get();
//        CouponDetail couponDetail = CouponDetail.builder()
//                .couponInfo("쿠폰 설명")
//                .detailInfo("상세 설명")
//                .usageInfo("사용 설명")
//                .build();
//        couponDetailRepository.save(couponDetail);
//        Coupon coupon = Coupon.builder()
//                .cno("123abc")
//                .coupon("쿠폰1")
//                .availability(true)
//                .user(user)
//                .discount(10)
//                .endDate(LocalDate.of(2022, 5, 9))
//                .expiration(false)
//                .couponDetail(couponDetail)
//                .build();
//        couponRepository.save(coupon);
//    }
//
//}

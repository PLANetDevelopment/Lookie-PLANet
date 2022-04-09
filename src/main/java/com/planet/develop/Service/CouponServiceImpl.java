package com.planet.develop.Service;

import com.planet.develop.DTO.CouponDto;
import com.planet.develop.Entity.Coupon;
import com.planet.develop.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public List<Coupon> getCouponList(String id) {
        List<Coupon> couponList = couponRepository.getCouponList(id);

        return null;
    }

}

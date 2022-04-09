package com.planet.develop.Repository;

import com.planet.develop.Entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    /** 쿠폰 조회 */
    @Query("select c from Coupon c where c.user.userId = :id")
    List<Coupon> getCouponList(@Param("id") String id);

}

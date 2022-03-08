package com.planet.develop.Repository;

import com.planet.develop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.userName= :username WHERE u.userId= :user_id")
    void updateName(@Param("username") String username,@Param("user_id") String user_id);

}

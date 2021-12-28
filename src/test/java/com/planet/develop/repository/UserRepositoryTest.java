package com.planet.develop.repository;

import com.planet.develop.entity.Mission;
import com.planet.develop.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
class UserRepositoryTest {
    @Autowired UserRepository userRepository;
    @Autowired MissionRepository missionRepository;
    @Test
    @Transactional
    @Rollback(false)
    public void testUser() {
        User user = new User();
        user.setName("memberA");
        userRepository.save(user);

        User findUser = userRepository.find(1L);
        Mission mission = new Mission();
        mission.changeMission(findUser);
        missionRepository.save(mission);

        List<Mission> missions = findUser.getMissions();
        System.out.println("==========================================");
        for (Mission mission1 : missions) {
            System.out.println("mission1 = " + mission1.getId());
        }
        System.out.println("==========================================");

    }
}
package com.planet.develop.repository;

import com.planet.develop.entity.Mission;
import com.planet.develop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest

public class MissionRespositoryTest {
    @Autowired UserRepository userRepository;
    @Autowired MissionRepository missionRepository;
    @Test
    @Transactional
    @Rollback(false)
    public void mission_status_테스트(){

        User user = new User();
        user.setName("memberA");
        userRepository.save(user);

        User findUser = userRepository.find(1L);

        Mission mission1 = new Mission();
        Mission mission2 = new Mission();
        mission1.changeMission(findUser);
        mission2.changeMission(findUser);
        missionRepository.save(mission1);
        missionRepository.save(mission2);

    }
}

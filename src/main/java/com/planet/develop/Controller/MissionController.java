package com.planet.develop.Controller;

import com.planet.develop.DTO.MissionCompleteDto;
import com.planet.develop.Entity.Mission;
import com.planet.develop.Entity.MissionComplete;
import com.planet.develop.Entity.User;
import com.planet.develop.Repository.MissionRepository;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Service.MissionCompleteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
public class MissionController {
    private final UserRepository userRepository;
    private final MissionCompleteService missionCompleteService;
    private final MissionRepository missionRepository;
    @PostMapping("/mission/{id}/{emoji}/{name}")
    public void main(@PathVariable("id") String id, @PathVariable("emoji") String emoji, @PathVariable("name")String name){
        User user = userRepository.findById(id).get();
        MissionComplete mission = MissionComplete.builder()
                .emoji(emoji)
                .name(name)
                .user(user)
                .build();


        missionCompleteService.save(mission);
    }

    //TODO 이모지 저장하기

    @GetMapping("/mission/{id}")
    public Result mission(@PathVariable("id") String id){
        User user = userRepository.findById(id).get();
        Mission mission = missionRepository.findMission(LocalDate.now());
        System.out.println("LocalDate.now() = " + LocalDate.now());
        System.out.println("------------------------------------------");
        System.out.println("mission.getName() = " + mission.getName());
        System.out.println("------------------------------------------");
        MissionCompleteDto todayMission = new MissionCompleteDto(mission.getName(),mission.getEmoji());

        List<MissionComplete> missions = missionCompleteService.findMissions(user);
        List<MissionCompleteDto> missionCompleteDtos = missions.stream()
                .map(o->new MissionCompleteDto(o.getName(),o.getEmoji()))
                .collect(Collectors.toList());
        return new Result(todayMission,missionCompleteDtos);

    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T todayMission;
        private T missions;
    }
}

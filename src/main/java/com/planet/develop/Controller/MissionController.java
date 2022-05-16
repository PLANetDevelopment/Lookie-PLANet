package com.planet.develop.Controller;

import com.planet.develop.DTO.MissionCompleteDto;
import com.planet.develop.Entity.Mission;
import com.planet.develop.Entity.MissionComplete;
import com.planet.develop.Entity.User;
import com.planet.develop.Repository.MissionRepository;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Security.DTO.AuthMemberDTO;
import com.planet.develop.Service.MissionCompleteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@PreAuthorize("hasRole('USER')")
public class MissionController {
    private final UserRepository userRepository;
    private final MissionCompleteService missionCompleteService;
    private final MissionRepository missionRepository;

    /** 에코미션 데이터 저장*/
    @PostMapping("/mission/{emoji}/{name}")
    public void main(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @PathVariable("emoji") String emoji, @PathVariable("name")String name){
        User user = userRepository.findById(authMemberDTO.getEmail()).get();
        MissionComplete mission = MissionComplete.builder()
                .emoji(emoji)
                .name(name)
                .user(user)
                .date(LocalDate.now())
                .build();
        missionCompleteService.save(mission);
    }

    /** 에코미션 페이지 조회*/
    @GetMapping("/mission/{year}/{month}")
    public Result mission(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @PathVariable("year") int year, @PathVariable("month") int month){
        User user = userRepository.findById(authMemberDTO.getEmail()).get();
        Mission mission = missionRepository.findMission(LocalDate.now());
        MissionCompleteDto todayMission = new MissionCompleteDto(mission.getName(),mission.getEmoji());

        List<MissionComplete> missions = missionCompleteService.findMissions(user,year,month);
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
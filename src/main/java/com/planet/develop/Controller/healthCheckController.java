package com.planet.develop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@PreAuthorize("permitAll()") // 모든 사용자가 접근 가능
public class healthCheckController {

    @GetMapping("/healthCheckController")
    public String main(){
        return "ok";
    }
}

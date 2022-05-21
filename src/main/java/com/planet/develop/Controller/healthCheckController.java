package com.planet.develop.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@PreAuthorize("permitAll()") // 모든 사용자가 접근 가능
public class healthCheckController {

    @GetMapping("/healthCheckController")
    public String main(){
        return "ok";
    }

    @GetMapping("/healthCheckLoginController")
    public String healthCheckLoginController(Principal principal){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        System.out.println(name);
        if (principal != null) {
            System.out.println("타입정보 : " + principal.getClass());
            System.out.println("ID정보 : " + principal.getName());
        }
        return "ok";
    }
}

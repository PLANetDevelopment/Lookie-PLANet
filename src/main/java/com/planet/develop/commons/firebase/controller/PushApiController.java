package com.planet.develop.commons.firebase.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.planet.develop.DTO.UserDTO;
import com.planet.develop.commons.firebase.service.FCMservice;
import com.planet.develop.commons.firebase.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PushApiController {
    private final NotificationService notificationService;
    private final FCMservice fcmservice;

    @PostMapping("/login")
    public ResponseEntity register(@RequestParam String userId,@RequestParam String token){
        notificationService.register(userId,token);
        System.out.println("token = " + notificationService.getToken(userId));
        System.out.println("userId = " + userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/logout")
    public void logout(@PathVariable String email) {
        notificationService.deleteToken(email);
    }

    @GetMapping("/test")
    public void test() throws FirebaseMessagingException {
        fcmservice.sendMessage();
    }


}


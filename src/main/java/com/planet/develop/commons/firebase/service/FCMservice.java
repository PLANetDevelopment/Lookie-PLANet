package com.planet.develop.commons.firebase.service;


import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class FCMservice {
    private  final NotificationService notificationService;

    public void sendMessage() {
        Map<String, String> tokens = notificationService.getTokens();

        for (String value : tokens.values()) {
            Message message = Message.builder()
                    .putData("title", "오늘의 에코 미션 ")
                    .putData("body", "에코미션 짜잔")
                    .setToken(value)
                    .build();

            send(message);
        }

    }

    public void send(Message message) {
        FirebaseMessaging.getInstance().sendAsync(message);
        ApiFuture<String> stringApiFuture = FirebaseMessaging.getInstance().sendAsync(message);
        System.out.println("stringApiFuture = " + stringApiFuture);

        System.out.println("message = " + message.getClass());
        System.out.println("message = " + message.getClass().getName());
        System.out.println("message = " + message.getClass());

    }

}


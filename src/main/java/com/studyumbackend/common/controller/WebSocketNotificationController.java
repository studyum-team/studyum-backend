package com.studyumbackend.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequestMapping
@RestController
@RequiredArgsConstructor
public class WebSocketNotificationController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/notify")
    @SendTo("/topic/notifications")
    public Map<String, Object> sendNotification(Map<String, Object> msg) {
        log.info("알림 메세지 수신 및 브로드캐스트 : {}", msg);
        return msg;
    }

    public void sendToUser(int userId, Map<String, Object> notification) {
        log.info("사용자 {}에게 개인 알림 전송 : {}", userId, notification);
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(userId), "/queue/notifications", notification);
    }

}
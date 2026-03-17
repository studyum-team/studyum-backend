package com.studyumbackend.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/chat-rooms")
public class ChatMessageController {

    /**
     * 메시지 목록 (커서 페이징)
     */
    // @GetMapping("/{roomId}/messages")

    /**
     * 메시지 전송 (REST fallback)
     */
    // @PostMapping("{roomId}/messages")

    /**
     * 메시지 삭제 (소프트)
     */
    // @DeleteMapping("{roomId}/messages/{messageId}")

    /**
     * 메시지 읽음 처리
     */
    // @PatchMapping("/{roomId}/messages/read")
}

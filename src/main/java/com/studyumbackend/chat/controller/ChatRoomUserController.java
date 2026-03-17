package com.studyumbackend.chat.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/chat-rooms")
public class ChatRoomUserController {

    /**
     * 채팅방 멤버 목록 조회
     */
    // @GetMapping("/{roomId}/members")

    /**
     * 멤버 역할 변경
     */
    // @PatchMapping("/{roomId}/members/{userId}/role")

    /**
     * 멤버 강퇴
     */
    // @DeleteMapping("/{roomId}/members/{userId}")

    /**
     * 채팅방 나가기 (본인)
     */
    // @DeleteMapping("/{roomId}/leave")
}

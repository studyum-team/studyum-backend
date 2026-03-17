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
public class ChatRoomInviteController {

    /**
     * 유저가 채팅방 직접 신청
     */
    // @PostMapping("/{roomId}/join")

    /**
     * 유저의 채팅방 신청 목록
     */
    // @GetMapping("/join")

    /**
     * 채팅방에서 멤버 초대
     */
    // @PostMapping("/{roomId}/invites")

    /**
     * 채팅방에서 멤버 초대/신청 확인 목록
     */
    // @GetMapping("/{roomId}/invites")

    /**
     * 채팅방에서 유저의 신청 수락/거절
     */
    // @PatchMapping("/{roomId}/invites/{inviteId}")

    /**
     * 유저가 채팅방 초대 수락/거절 (피초대자 본인)
     */
    // @PatchMapping("/invites/{inviteId}/respond")
}

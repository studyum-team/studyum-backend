package com.studyumbackend.friend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/friends/requests")
public class FriendRequestController {
    /**
     * 친구 요청 보내기
     */
     // @PostMapping()


    /**
     * 받은 친구 요청 목록
     */
    // @GetMapping("/received")


    /**
     * 보낸 친구 요청 목록
     */
    // @GetMapping("sent")


    /**
     * 친구 요청 수락/거절
     */
    // @PatchMapping("/{requestId}")


    /**
     * 보낸 친구 요청 취소
     */
    // @DeleteMapping("/{requestId}")


}

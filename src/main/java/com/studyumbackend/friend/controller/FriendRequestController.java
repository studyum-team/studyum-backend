package com.studyumbackend.friend.controller;

import com.studyumbackend.common.util.AuthUtil;
import com.studyumbackend.friend.model.dto.FriendRequest;
import com.studyumbackend.friend.model.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/friends/requests")
public class FriendRequestController {

    private final AuthUtil authUtil;
    private final FriendService friendService;

    // ==========================================
    //            Friend Request Process
    // ==========================================

    /**
     * 친구 요청 보내기
     * @param authHeader JWT 토큰 헤더
     * @param receiverId 상대방 유저 ID
     * @return 상태코드 200
     */
    @PostMapping("/{receiverId}")
    public ResponseEntity<Void> postFriendRequest(@RequestHeader("Authorization") String authHeader,
                                                  @PathVariable Long receiverId) {
        Long requesterId = authUtil.getCurrentUserId(authHeader);
        friendService.postFriendRequest(requesterId, receiverId);
        return ResponseEntity.ok().build();
    }

    /**
     * 친구 요청 목록 조회 (수신 및 신청 합계)
     * @param authHeader JWT 토큰 헤더
     * @return 수신/신청 목록 Map + 상태코드 200
     */
    @GetMapping()
    public ResponseEntity<Map<String, List<FriendRequest>>> getFriendRequests(@RequestHeader("Authorization") String authHeader) {
        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        Map<String, List<FriendRequest>> result = friendService.getFriendRequests(currentUserId);
        return ResponseEntity.ok(result);
    }

    /**
     * 친구 요청 수락/거절
     * @param friendRequest 친구 요청 객체 (id 및 관련 정보 포함)
     * @param status ACCEPTED 또는 REJECTED
     * @return 상태코드 200
     */
    @PatchMapping("/{status}")
    public ResponseEntity<Void> patchFriendRequest(@RequestBody FriendRequest friendRequest,
                                                   @PathVariable String status) {
        friendService.patchFriendRequest(friendRequest, status);
        return ResponseEntity.ok().build();
    }

    /**
     * 보낸 친구 요청 취소
     * @param requestId 친구 요청 ID
     * @return 상태코드 204
     */
    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable Long requestId) {
        friendService.deleteFriendRequest(requestId);
        return ResponseEntity.noContent().build();
    }
}
package com.studyumbackend.friend.controller;

import com.studyumbackend.common.util.AuthUtil;
import com.studyumbackend.friend.model.dto.Friend;
import com.studyumbackend.friend.model.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendController {

    private final AuthUtil authUtil;
    private final FriendService friendService;

    // ==========================================
    //              Friend Management
    // ==========================================

    /**
     * 본인 친구 목록 조회
     * @param authHeader JWT 토큰 헤더
     * @return 친구 목록 + 상태코드 200
     */
    @GetMapping()
    public ResponseEntity<List<Friend>> getFriends(@RequestHeader("Authorization") String authHeader) {
        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        List<Friend> friends = friendService.getFriends(currentUserId);
        return ResponseEntity.ok(friends);
    }

    /**
     * 친구 삭제 (관계 끊기)
     * @param friendRelationId 친구 관계 PK (friend_relation_id)
     * @return 상태코드 204
     */
    @DeleteMapping("/{friendRelationId}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long friendRelationId) {
        friendService.deleteFriend(friendRelationId);
        return ResponseEntity.noContent().build();
    }
}
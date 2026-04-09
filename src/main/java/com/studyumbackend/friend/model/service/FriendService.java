package com.studyumbackend.friend.model.service;

import com.studyumbackend.friend.model.dto.Friend;
import com.studyumbackend.friend.model.dto.FriendRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FriendService {

    // 친구 요청 신청
    void postFriendRequest(Long userId, Long friendId);

    // 친구 요청 송수신 목록
    Map<String, List<FriendRequest>> getFriendRequests(Long userId);

    // 친구 요청 취소
    void deleteFriendRequest(Long friendRequestId);

    // 친구 요청 상태 변경
    void patchFriendRequest(FriendRequest friendRequest, String status);

    // 마이페이지 친구 요청 및 상태 조회
    FriendRequest getFriendRequestStatus(Long userId, Long friendId);

    // 친구 목록
    List<Friend> getFriends(Long userId);

    // 친구 삭제
    void deleteFriend(Long friendRelationId);

}

package com.studyumbackend.friend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest {
    // 요청 고유 식별자 (Auto)
    private long friendRequestId;
    // 친구 요청 보낸 유저
    private long requesterId;
    // 친구 요청 받은 유저
    private long receiverId;
    // 처리 상태 (PENDING, ACCEPTED, REJECTED)
    private String friendRequestStatus;
    // 요청 일시
    private String friendRequestCreatedAt;
}

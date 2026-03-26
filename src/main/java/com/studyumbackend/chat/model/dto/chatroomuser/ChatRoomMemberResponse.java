package com.studyumbackend.chat.model.dto.chatroomuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChatRoomMemberListResponse 중 채팅방 멤버 리스트 데이터
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomMemberResponse {
    // 채팅방 유저 ID
    private Long chatRoomUserId;
    // 단일 유저 데이터 객체
    private ChatRoomSingleUserResponse user;  // 중첩 객체
    // 유저 역할
    private String chatRoomUserRole;
    // 유저 채팅방 참여 일자
    private String chatRoomUserJoinedAt;
}

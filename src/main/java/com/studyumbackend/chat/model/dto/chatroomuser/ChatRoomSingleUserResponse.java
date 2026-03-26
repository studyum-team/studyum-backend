package com.studyumbackend.chat.model.dto.chatroomuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChatRoomMemberResponse 내부에서 사용되는 중첩 객체
 * 채팅방 유저 리스트 중 단일 유저 데이터
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomSingleUserResponse {
    private Long userId;
    private String userName;
    private String userProfileImage;
}

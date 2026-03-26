package com.studyumbackend.chat.model.dto.chatroomuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomUser {
    // 참여 고유 식별자 (Auto)
    private Long chatRoomUserId;
    // 참여중인 채팅방
    private Long chatRoomId;
    // 채팅방 참여자
    private Long userId;
    // 채팅방 멤버 등급
    private String chatRoomUserRole;
    // 마지막 읽은 메세지 ID
    private Long chatRoomUserLastReadMessageId;
    // 채팅방 참여 일시
    private String chatRoomUserJoinedAt;

}

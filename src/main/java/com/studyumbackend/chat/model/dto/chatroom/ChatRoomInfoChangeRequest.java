package com.studyumbackend.chat.model.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 채팅방 정보 수정 요청 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomInfoChangeRequest {
    // 채팅방 명칭
    private String chatRoomName;
    // 채팅방 소개
    private String chatRoomDescription;
    // 채팅방 최대인원
    private int chatRoomMaxMember;
    // 채팅방 모집 상태
    private String chatRoomRecruitStatus;
}

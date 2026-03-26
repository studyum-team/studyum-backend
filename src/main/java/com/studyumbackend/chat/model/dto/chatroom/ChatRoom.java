package com.studyumbackend.chat.model.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    // 채팅방 고유 식별자 (Auto)
    private Long chatRoomId;
    // 채팅방 카테고리 ID
    private Long categoryId;
    // 채팅방 이름
    private String chatRoomName;
    // 채팅방 소개
    private String chatRoomDescription;
    // 채팅방 프로필
    private String chatRoomImage;
    // 채팅방 공개 여부
    private String chatRoomVisibility;
    // 채팅방 최대 입장 인원
    private Integer chatRoomMaxMember;
    // 채팅방 참여 인원
    private Integer chatRoomCurrentMember;
    // 채팅방 모집 상태
    private String chatRoomRecruitStatus;
    // 채팅방 생성 일자
    private String chatRoomCreatedAt;
}
package com.studyumbackend.chat.model.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * v_chat_room_list 뷰 테이블 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomListDTO {
    // 사용자 ID
    private Long userId;
    // 사용자 역할
    private String myRole;
    // 채팅방 참여 일자
    private String chatRoomUserJoinedAt;

    // 채팅방 ID
    private Long chatRoomId;
    // 채팅방 명칭
    private String chatRoomName;
    // 채팅방 프로필 이미지
    private String chatRoomImage;
    // 채팅방 설명
    private String chatRoomDescription;
    // 채팅방 공개 여부
    private String chatRoomVisibility;
    // 채팅방 최대 인원
    private Integer chatRoomMaxMember;
    // 채팅방 현재 인원
    private Integer chatRoomCurrentMember;
    // 채팅방 모집 상태
    private String chatRoomRecruitStatus;

    // 카테고리 ID
    private Long categoryId;
    // 카테고리 명칭
    private String categoryName;

    // 마지막 메세지 ID
    private Long lastMessageId;
    // 마지막 메세지 내용
    private String lastMessageContent;
    // 마지막 메세지 유형
    private String lastMessageType;
    // 마지막 메세지 발신/수신 시점
    private String lastMessageAt;
    // 안 읽은 메세지 수
    private Long unreadCount;
}
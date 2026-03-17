package com.studyumbackend.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomInvite {
    // 초대/신청 고유 식별자 (Auto)
    private long chatRoomInviteId;
    // 대상 채팅방
    private long chatRoomId;
    // null = 본인싱청, not null = 소속 인원 초대
    private long inviterId;
    // 입장 대상 유저
    private long inviteeId;
    // 입장 종류
    private String inviteType;
    // 처리 상태
    private String chatRoomInviteStatus;
    // 신청/초대 일시
    private String chatRoomInviteCreatedAt;
}

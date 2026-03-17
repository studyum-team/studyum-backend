package com.studyumbackend.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    // 메세지 고유 식별자 (Auto)
    private long chatMessageId;
    // 메세지가 속한 채팅방
    private long chatRoomId;
    // 메세지 발신자
    private long chatMessageSenderId;
    // 메세지 내용
    private String chatMessageContent;
    // 메세지 타입
    private String chatMessageType;
    // 소프트 삭제 여부
    private boolean chatMessageIsDeleted;
    // 발송 일시
    private String chatMessageCreatedAt;
}

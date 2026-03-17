package com.studyumbackend.notification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    // 알림 고유 식별자 (AUTO)
    private long notificationId;
    // 알림 수신 유저
    private long userId;
    // 알림 유형
    private String notificationType;
    // 관련 대상 ID
    private long notificationReferenceId;
    // 알림 메세지 본문
    private String notificationMessage;
    // 읽음 여부
    private boolean notificationIsRead;
    // 알림 생성 일시
    private String notificationCreatedAt;
}

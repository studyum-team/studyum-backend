package com.studyumbackend.notification.model.service;

import com.studyumbackend.notification.model.dto.Notification;

import java.util.List;

public interface NotificationService {
    // 알림 목록 전체 조회
    List<Notification> getAllNotification(Long userId);

    // 알림 목록 읽지않음 조회
    List<Notification> getUnreadNotification(Long userId);

    // 알림 단건 읽음 처리
    void putNotification(Long notificationId);

    // 알림 전체 읽음 처리
    void putAllNotification(Long userId);

    // 알림 단건 삭제
    void deleteNotification(Long notificationId);
}

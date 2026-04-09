package com.studyumbackend.notification.model.mapper;

import com.studyumbackend.notification.model.dto.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
    // 알림 목록 전체 조회
    List<Notification> selectAllNotification(Long userId);

    // 알림 목록 읽지않음 조회
    List<Notification> selectUnreadNotification(Long userId);

    // 알림 단건 읽음 처리
    int updateNotification(Long notificationId);

    // 알림 전체 읽음 처리
    int updateAllNotification(Long userId);

    // 알림 단건 삭제
    int deleteNotification(Long notificationId);
}

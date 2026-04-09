package com.studyumbackend.notification.model.service;

import com.studyumbackend.common.exception.NotFoundException;
import com.studyumbackend.notification.model.dto.Notification;
import com.studyumbackend.notification.model.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationMapper notificationMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Notification> getAllNotification(Long userId) {
        log.info("💡 알림 조회 userId: {}", userId);

        if (userId == null) {
            throw new NotFoundException("유저 정보가 없습니다.");
        }

        List<Notification> result = notificationMapper.selectAllNotification(userId);
        log.info("💡 알림 조회 성공 userId: {}", userId);

        return result != null ? result : new ArrayList<>();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Notification> getUnreadNotification(Long userId) {
        log.info("💡 읽지 않음 알림 조회 userId: {}", userId);

        if (userId == null) {
            throw new NotFoundException("유저 정보가 없습니다.");
        }

        List<Notification> result = notificationMapper.selectUnreadNotification(userId);
        log.info("💡 읽지 않음 알림 조회 성공 userId: {}", userId);

        return result != null ? result : new ArrayList<>();
    }

    @Transactional
    @Override
    public void putNotification(Long notificationId) {
        log.info("💡 알림 읽음 확인 notificationId: {}", notificationId);

        int result = notificationMapper.updateNotification(notificationId);

        if(result != 1){
            log.info("💡 알림 읽기 처리 실패 notificationId: {}", notificationId);
            throw new RuntimeException("읽기 처리에 실패했습니다.");
        }

        log.info("💡 알림 읽음 처리 완료 notificationId: {}", notificationId);
    }

    @Transactional
    @Override
    public void putAllNotification(Long userId) {
        log.info("💡 알림 전체 읽음 확인 userId: {}", userId);

        int result = notificationMapper.updateAllNotification(userId);

        if(result < 1){
            log.info("💡 알림 전체 읽기 처리 실패 userId: {}", userId);
            throw new RuntimeException("전체 읽기 처리에 실패했습니다.");
        }

        log.info("💡 알림 전체 읽음 처리 완료 userId: {}", userId);
    }

    @Transactional
    @Override
    public void deleteNotification(Long notificationId) {
        log.info("💡 알림 삭제 시도 notificationId: {}", notificationId);

        int result = notificationMapper.deleteNotification(notificationId);

        if(result != 1){
            log.info("💡 알림 삭제 실패 notificationId: {}", notificationId);
            throw new RuntimeException("알림 삭제에 실패했습니다.");
        }

        log.info("💡 알림 삭제 완료 notificationId: {}", notificationId);
    }
}

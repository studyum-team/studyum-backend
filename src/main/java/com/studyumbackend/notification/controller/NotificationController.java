package com.studyumbackend.notification.controller;

import com.studyumbackend.common.util.AuthUtil;
import com.studyumbackend.notification.model.dto.Notification;
import com.studyumbackend.notification.model.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final AuthUtil authUtil;

    /**
     * 알림 목록 조회
     * @param authHeader JWT 토큰
     * @return 알림 리스트 + 상태 코드 200
     */
    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotification(@RequestHeader("Authorization") String authHeader) {
        Long userId = authUtil.getCurrentUserId(authHeader);
        List<Notification> result = notificationService.getAllNotification(userId);
        return ResponseEntity.ok(result);
    }

    /**
     * 읽지 않음 알림 목록 조회
     * @param authHeader JWT 토큰
     * @return 알림 리스트 + 상태 코드 200
     */
    @GetMapping("/unread")
    public ResponseEntity<List<Notification>> getUnreadNotification(@RequestHeader("Authorization") String authHeader) {
        Long userId = authUtil.getCurrentUserId(authHeader);
        List<Notification> result = notificationService.getUnreadNotification(userId);
        return ResponseEntity.ok(result);
    }

    /**
     * 알림 단건 읽음 처리
     * @param notificationId 읽을 알림 ID
     * @return 상태 코드 200
     */
    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<Void> putNotification(@PathVariable Long notificationId) {
        notificationService.putNotification(notificationId);
        return ResponseEntity.ok().build();
    }

    /**
     * 알림 전체 읽음 처리
     * @param authHeader 전체 읽음 처리할 유저 ID
     * @return 상태 코드 200
     */
    @PatchMapping("/read-all")
    public ResponseEntity<Void> putAllNotification(@RequestHeader("Authorization") String authHeader) {
        Long userId = authUtil.getCurrentUserId(authHeader);
        notificationService.putAllNotification(userId);
        return ResponseEntity.ok().build();
    }

    /**
     * 알림 단건 삭제
     * @param notificationId 삭제할 알림 ID
     * @return 상태 코드 200
     */
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok().build();
    }
}

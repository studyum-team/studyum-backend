package com.studyumbackend.calendar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEvent {
    // 일정 고유 식별자 (Auto)
    private Long calendarEventId;
    // 개인 일정 소유자, 스터디 일정이면 null
    private Long userId;
    // 스터디 공유 일정의 채팅방, 개인 일정이면 null
    private Long chatRoomId;
    // 연결된 목표
    private Long userGoalId;
    // 일정 제목
    private String calendarEventTitle;
    // 일정 상세 내용
    private String calendarEventDescription;
    // 시작 일시
    private LocalDateTime calendarEventStartTime;
    // 종료 일시
    private LocalDateTime calendarEventEndTime;
    // 일정 완료 여부
    private boolean calendarEventIsDone;
}

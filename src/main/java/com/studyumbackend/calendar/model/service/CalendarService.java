package com.studyumbackend.calendar.model.service;

import com.studyumbackend.calendar.model.dto.CalendarEvent;
import com.studyumbackend.calendar.model.dto.CalendarEventWithGoalDTO;

import java.util.List;

public interface CalendarService {
    // 홈 화면
    // 개인 / 스터디룸 일정 생성
    void postCalendarEvent(CalendarEvent calendarEvent);

    // 일정 목록 조회
    List<CalendarEvent> getCalendarEvent(Long userId);

    // 일정 상세 조회
    CalendarEventWithGoalDTO getCalendarEventDetail(Long calendarEventId);

    // 일정 수정
    void putCalendarEvent(CalendarEvent calendarEvent);

    // 일정 완료 체크
    void putCalendarEventCheck(Long calendarEventId);

    // 일정 삭제
    void deleteCalendarEvent(Long calendarEventId);

    // 스터디룸
    // 스터디 공유 일정 목록
    List<CalendarEvent> getChatRoomList(Long chatRoomId);
}

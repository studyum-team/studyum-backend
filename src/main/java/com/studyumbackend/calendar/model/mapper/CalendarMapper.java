package com.studyumbackend.calendar.model.mapper;

import com.studyumbackend.calendar.model.dto.CalendarEvent;
import com.studyumbackend.calendar.model.dto.CalendarEventWithGoalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarMapper {

    // 홈 화면
    // 개인 일정 생성
    int insertCalendarEvent(CalendarEvent calendarEvent);

    // 일정 목록 조회
    List<CalendarEvent> selectCalendarEvent(Long userId);

    // 일정 상세 조회
    CalendarEventWithGoalDTO selectCalendarEventDetail(Long calendarEventId);

    // 일정 수정
    int updateCalendarEvent(CalendarEvent calendarEvent);

    // 일정 완료 체크
    int updateCalendarEventCheck(Long calendarEventId);

    // 일정 삭제
    int deleteCalendarEvent(Long calendarEventId);

    // 스터디룸
    // 스터디룸 일정 생성
    int insertChatRoomCalendarEvent(CalendarEvent calendarEvent);

    // 스터디 공유 일정 목록
    List<CalendarEvent> selectChatRoomList(Long chatRoomId);
}

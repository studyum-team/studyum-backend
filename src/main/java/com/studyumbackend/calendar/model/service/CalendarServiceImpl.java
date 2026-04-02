package com.studyumbackend.calendar.model.service;

import com.studyumbackend.calendar.model.dto.CalendarEvent;
import com.studyumbackend.calendar.model.dto.CalendarEventWithGoalDTO;
import com.studyumbackend.calendar.model.mapper.CalendarMapper;
import com.studyumbackend.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarMapper calendarMapper;

    @Transactional
    @Override
    public void postCalendarEvent(CalendarEvent calendarEvent) {
        log.info("💡 일정 생성 userId: {}, chatRoomId: {}",
                calendarEvent.getUserId(), calendarEvent.getChatRoomId());

        int result;

        if (calendarEvent.getUserId() != null) {
            // 개인 일정
            result = calendarMapper.insertCalendarEvent(calendarEvent);
        } else {
            // 스터디룸 일정
            result = calendarMapper.insertChatRoomCalendarEvent(calendarEvent);
        }

        if (result != 1) {
            log.info("💡 일정 생성 실패");
            throw new RuntimeException("일정 생성에 실패했습니다.");
        }

        log.info("💡 일정 생성 성공");
    }

    @Transactional(readOnly = true)
    @Override
    public List<CalendarEvent> getCalendarEvent(Long userId) {
        log.info("💡 일정 조회 userId: {}", userId);

        if (userId == null) {
            throw new NotFoundException("유저 정보가 없습니다.");
        }

        List<CalendarEvent> result = calendarMapper.selectCalendarEvent(userId);
        log.info("💡 일정 조회 성공 userId: {}", userId);

        return result != null ? result : new ArrayList<>();
    }

    @Transactional(readOnly = true)
    @Override
    public CalendarEventWithGoalDTO getCalendarEventDetail(Long calendarEventId) {
        log.info("💡 일정 상세 조회 calendarEventId: {}", calendarEventId);

        if (calendarEventId == null) {
            throw new NotFoundException("유저 정보가 없습니다.");
        }

        CalendarEventWithGoalDTO result = calendarMapper.selectCalendarEventDetail(calendarEventId);

        if (result == null) {
            log.info("💡 일정 상세 조회 실패 calendarEventId: {}", calendarEventId);
            throw new NotFoundException("해당하는 일정이 없습니다.");
        }

        log.info("💡 일정 상세 조회 성공 calendarEventId: {}", calendarEventId);
        return result;
    }

    @Transactional
    @Override
    public void putCalendarEvent(CalendarEvent calendarEvent) {
        log.info("💡 일정 수정 시작 calendarEventId: {}", calendarEvent.getCalendarEventId());

        int result = calendarMapper.updateCalendarEvent(calendarEvent);

        if(result != 1){
            log.info("💡 일정 수정 실패 calendarEventId: {}", calendarEvent.getCalendarEventId());
            throw new RuntimeException("일정 수정에 실패했습니다.");
        }

        log.info("💡 일정 수정 성공 calendarEventId: {}", calendarEvent.getCalendarEventId());
    }

    @Transactional
    @Override
    public void putCalendarEventCheck(Long calendarEventId) {
        log.info("💡 일정 상태 확인 calendarEventId: {}", calendarEventId);

        int result = calendarMapper.updateCalendarEventCheck(calendarEventId);

        if(result != 1){
            log.info("💡 일정 상태 변경 실패 calendarEventId: {}", calendarEventId);
            throw new RuntimeException("일정 상태 변경에 실패했습니다.");
        }

        log.info("💡 일정 상태 변경 성공 calendarEventId: {}", calendarEventId);
    }

    @Transactional
    @Override
    public void deleteCalendarEvent(Long calendarEventId) {
        log.info("💡 일정 삭제 시도 calendarEventId: {}", calendarEventId);

        int result = calendarMapper.deleteCalendarEvent(calendarEventId);

        if(result != 1){
            log.info("💡 일정 삭제 실패 calendarEventId: {}", calendarEventId);
            throw new RuntimeException("일정 삭제에 실패했습니다.");
        }

        log.info("💡 일정 삭제 성공 calendarEventId: {}", calendarEventId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CalendarEvent> getChatRoomList(Long chatRoomId) {
        log.info("💡 스터디룸 일정 조회 chatRoomId: {}", chatRoomId);

        if (chatRoomId == null) {
            throw new NotFoundException("스터디룸 정보가 없습니다.");
        }

        List<CalendarEvent> result = calendarMapper.selectChatRoomList(chatRoomId);
        log.info("💡 스터디룸 일정 조회 성공 chatRoomId: {}", chatRoomId);

        return result != null ? result : new ArrayList<>();
    }
}

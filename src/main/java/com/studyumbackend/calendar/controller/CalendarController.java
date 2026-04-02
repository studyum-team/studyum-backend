package com.studyumbackend.calendar.controller;

import com.studyumbackend.calendar.model.dto.CalendarEvent;
import com.studyumbackend.calendar.model.dto.CalendarEventWithGoalDTO;
import com.studyumbackend.calendar.model.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * 일정 생성 (개인 또는 스터디 공유)
     *
     * @param calendarEvent calendar 객체
     * @return 생성 후 조회 + 상태 코드 200
     */
    @PostMapping("/events")
    public ResponseEntity<List<CalendarEvent>> postCalendarEvent(@RequestBody CalendarEvent calendarEvent) {
        calendarService.postCalendarEvent(calendarEvent);
        if (calendarEvent.getUserId() != null) {
            List<CalendarEvent> result = calendarService.getCalendarEvent(calendarEvent.getUserId());
            return ResponseEntity.ok(result);
        }

        List<CalendarEvent> result = calendarService.getChatRoomList(calendarEvent.getChatRoomId());
        return ResponseEntity.ok(result);
    }

    /**
     * 일정 목록 조회
     *
     * @param userId 전달받는 유저 ID
     * @return 일정 리스트 + 상태 코드 200
     */
    @GetMapping("/events")
    public ResponseEntity<List<CalendarEvent>> getCalendarEvent(@RequestParam Long userId) {
        List<CalendarEvent> result = calendarService.getCalendarEvent(userId);
        return ResponseEntity.ok(result);
    }

    /**
     * 일정 단건 조회
     *
     * @param calendarEventId 전달받은 일정 ID
     * @return 일정 상세 목록 조회 + 상태 코드 200
     */
    @GetMapping("/events/{eventId}")
    public ResponseEntity<CalendarEventWithGoalDTO> getCalendarEventDetail(@PathVariable("eventId") Long calendarEventId) {
        CalendarEventWithGoalDTO result = calendarService.getCalendarEventDetail(calendarEventId);
        return ResponseEntity.ok(result);
    }

    /**
     * 일정 수정
     *
     * @param calendarEvent   제목, 마지막 날짜가 들어있는 객체
     * @param calendarEventId 수정할 일정 ID
     * @return 상태 코드 200
     */
    @PatchMapping("/events/{eventId}")
    public ResponseEntity<Void> putCalendarEvent(@RequestBody CalendarEvent calendarEvent, @PathVariable("eventId") Long calendarEventId) {
        calendarEvent.setCalendarEventId(calendarEventId);
        calendarService.putCalendarEvent(calendarEvent);
        return ResponseEntity.ok().build();
    }

    /**
     * 일정 완료 체크
     *
     * @param calendarEventId 전달받은 일정 ID
     * @return 상태 코드 200
     */
    @PatchMapping("/events/{eventId}/done")
    public ResponseEntity<Void> putCalendarEventCheck(@PathVariable("eventId") Long calendarEventId) {
        calendarService.putCalendarEventCheck(calendarEventId);
        return ResponseEntity.ok().build();
    }

    /**
     * 일정 삭제
     *
     * @param calendarEventId 전달받은 일정 ID
     * @return 상태 코드 200
     */
    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Void> deleteCalendarEvent(@PathVariable("eventId") Long calendarEventId) {
        calendarService.deleteCalendarEvent(calendarEventId);
        return ResponseEntity.ok().build();
    }

    /**
     * 스터디 일정 공유 목록
     * @param roomId 전달받은 스터디룸 ID
     * @return 스터디룸 일정 조회 + 상태 코드 200
     */
    @GetMapping("/{roomId}/events")
    public ResponseEntity<List<CalendarEvent>> getChatRoomList(@PathVariable Long roomId) {
        List<CalendarEvent> result = calendarService.getChatRoomList(roomId);
        return ResponseEntity.ok(result);
    }
}

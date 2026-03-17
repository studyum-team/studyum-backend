package com.studyumbackend.calendar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class CalendarController {
    /**
     * 일정 생성 (개인 또는 스터디 공유)
     */
    // @PostMapping("/events")

    /**
     * 일정 목록 조회 (월별/기간)
     */
    // @GetMapping("/events")

    /**
     * 일정 단건 조회
     */
    // @GetMapping("/events/{eventId}")

    /**
     * 일정 수정
     */
    // @PatchMapping("/events/{eventId}")

    /**
     * 일정 완료 체크 (목표 달성률 반영)
     */
    // @PatchMapping("/events/{eventId}/done")

    /**
     * 일정 삭제
     */
    // @DeleteMapping("/events/{eventId}")

    /**
     * 스터디 일정 공유 목록
     */
    // @GetMapping("/{roomId}/events")
}

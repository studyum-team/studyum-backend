package com.studyumbackend.goal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/goals")
public class GoalController {
    /**
     * 목표 생성 (ACTIVE 최대 3개)
     */
    //@PostMapping()


    /**
     * 본인 목표 목록 조회
     */
    // @GetMapping()


    /**
     * 본인 목표 단건 조회
     */
    // @GetMapping("/{goalId}")


    /**
     * 목표 수정
     * (기존 목표 ARCHIVED -> 신규 목표 INSERT 트랜잭션)
     */
    // @PatchMapping("/{goalId}")


    /**
     * 목표 삭제 (ARCHIVED 처리)
     */
    // @DeleteMapping("/{goalId}")


    /**
     * 목표 상태 변경 (달성/포기)
     */
    // @PatchMapping("/{goalId}/status")

}

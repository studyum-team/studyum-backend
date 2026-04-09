package com.studyumbackend.goal.controller;

import com.studyumbackend.common.util.AuthUtil;
import com.studyumbackend.goal.model.dto.Goal;
import com.studyumbackend.goal.model.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;
    private final AuthUtil authUtil;

    /**
     * 목표 생성 (ACTIVE 최대 3개)
     * @param authHeader JWT 토큰
     * @param goal goal 객체
     * @return
     */
    @PostMapping
    public ResponseEntity<List<Goal>> postGoal(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Goal goal) {

        Long userId = authUtil.getCurrentUserId(authHeader);
        goal.setUserId(userId);
        goalService.postGoal(goal);

        List<Goal> result = goalService.getGoal(goal.getUserId());
        return ResponseEntity.ok().body(result);
    }

    /**
     * 본인 목표 목록 조회
     * @param authHeader JWT 토큰
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Goal>> getGoal(
            @RequestHeader("Authorization") String authHeader){
        Long userId = authUtil.getCurrentUserId(authHeader);
        List<Goal> result = goalService.getGoal(userId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * 목표 수정 및 생성
     * @param userGoalId 유저 목표 ID
     * @return 상태 코드 200
     */
    @PatchMapping("/{goalId}")
    public ResponseEntity<List<Goal>> updateGoal(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable("goalId") Long userGoalId,
            @RequestBody Goal newGoal) {

        Long userId = authUtil.getCurrentUserId(authHeader);
        newGoal.setUserId(userId);
        goalService.putGoal(userGoalId, newGoal);

        List<Goal> result = goalService.getGoal(userId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * 목표 삭제 (ARCHIVED 처리)
     * @param userGoalId 유저 목표 ID
     * @return 상태 코드 200
     */
    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable("goalId") Long userGoalId){
         goalService.deleteGoal(userGoalId);
         return ResponseEntity.ok().build();
     }

    /**
     * 목표 상태 변경 (달성/포기)
     * 프론트에서 확인 필요
     */
    // @PatchMapping("/{goalId}/status")

}

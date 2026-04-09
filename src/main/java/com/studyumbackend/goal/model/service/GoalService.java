package com.studyumbackend.goal.model.service;

import com.studyumbackend.goal.model.dto.Goal;

import java.util.List;

public interface GoalService {
    // 목표 생성
    void postGoal(Goal goal);

    // 목표 조회
    List<Goal> getGoal(Long userId);

    // 목표 수정
    void putGoal(Long userGoalId, Goal goal);

    // 목표 삭제
    void deleteGoal(Long userGoalId);
}

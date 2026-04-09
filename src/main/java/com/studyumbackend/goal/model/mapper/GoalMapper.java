package com.studyumbackend.goal.model.mapper;

import com.studyumbackend.goal.model.dto.Goal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoalMapper {
    // 목표 생성
    int insertGoal(Goal goal);

    // 목표 조회
    List<Goal> selectGoal(Long userId);

    // 목표 수정
    int updateGoal(Long userGoalId);

    // 목표 삭제
    int deleteGoal(Long userGoalId);
}

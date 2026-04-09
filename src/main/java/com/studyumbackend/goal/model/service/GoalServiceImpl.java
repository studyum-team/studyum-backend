package com.studyumbackend.goal.model.service;

import com.studyumbackend.common.exception.NotFoundException;
import com.studyumbackend.goal.model.dto.Goal;
import com.studyumbackend.goal.model.mapper.GoalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalMapper goalMapper;

    @Transactional
    @Override
    public void postGoal(Goal goal) {
        log.info("💡 목표 생성 userId: {}", goal.getUserId());

        int result = goalMapper.insertGoal(goal);
        if (result != 1) {
            log.info("💡 목표 생성 실패 userId: {}", goal.getUserId());
            throw new RuntimeException("목표 생성에 실패했습니다.");
        }

        log.info("💡 목표 생성 성공 userId: {}", goal.getUserId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Goal> getGoal(Long userId) {
        log.info("💡 목표 조회 userId: {}", userId);

        if (userId == null) {
            throw new NotFoundException("유저 정보가 없습니다.");
        }

        List<Goal> result = goalMapper.selectGoal(userId);
        log.info("💡 목표 조회 성공 userId: {}", userId);

        return result != null ? result : new ArrayList<>();
    }

    @Transactional
    @Override
    public void putGoal(Long userGoalId, Goal goal) {
        log.info("💡 목표 수정 시작 userGoalId: {}", userGoalId);

        int result = goalMapper.updateGoal(userGoalId);

        if (result != 1) {
            log.info("💡 목표 수정 실패 userGoalId: {}", userGoalId);
            throw new RuntimeException("목표 수정에 실패했습니다.");
        }

        log.info("💡 목표 수정 성공 userGoalId: {}", userGoalId);

        log.info("💡 신규 목표 생성 userId: {}", goal.getUserId());
        int insertResult = goalMapper.insertGoal(goal);

        if (insertResult != 1) {
            log.info("💡 신규 목표 생성 실패 userId: {}", goal.getUserId());
            throw new RuntimeException("목표 생성에 실패했습니다.");
        }

        log.info("💡 신규 목표 생성 완료 userId: {}", goal.getUserId());
    }

    @Transactional
    @Override
    public void deleteGoal(Long userGoalId) {
        log.info("💡 목표 삭제 시작 userGoalId: {}", userGoalId);

        int result = goalMapper.deleteGoal(userGoalId);

        if (result != 1) {
            log.info("💡 목표 삭제 실패 userGoalId: {}", userGoalId);
            throw new RuntimeException("목표 삭제에 실패했습니다.");
        }

        log.info("💡 목표 삭제 성공 userGoalId: {}", userGoalId);
    }
}

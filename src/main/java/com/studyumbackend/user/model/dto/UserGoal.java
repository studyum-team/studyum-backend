package com.studyumbackend.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGoal {
    // 회원 목표 식별자
    private Long userGoalId;
    // 회원 ID
    private Long userId;
    // 목표 제목
    private String userGoalTitle;
    // 목표 기한
    private String userGoalTargetDate;
    // 목표 상태
    private String goalStatus;
    // 목표 생성일
    private String goalCreatedAt;
    // 목표 수정일
    private String goalUpdatedAt;
}

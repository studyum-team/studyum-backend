package com.studyumbackend.goal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    // 목표 고유 식별자 (Auto)
    private long userGoalId;
    // 목표 설정한 유저
    private long userId;
    // 목표 제목
    private String userGoalTitle;
    // 목표 달성 기한
    private String userGoalTargetDate;
    // 목표 상태 (ACTIVE, COMPLETED, ARCHIVED)
    private String goalStatus;
    // 목표 생성 일시
    private String goalCreatedAt;
    // 상태 변경 일시
    private String goalUpdatedAt;
}

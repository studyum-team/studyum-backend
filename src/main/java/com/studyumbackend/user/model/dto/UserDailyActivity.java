package com.studyumbackend.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDailyActivity {
    // 활동 식별자
    private Long activityId;
    // 회원 ID
    private Long userId;
    // 활동 날짜
    private String activityDate;
    // 활동 여부
    private Boolean isActive;
    // 활동 생성일
    private String CreatedAt;
    // 활동 수정일
    private String UpdatedAt;
}

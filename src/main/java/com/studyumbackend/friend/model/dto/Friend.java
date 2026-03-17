package com.studyumbackend.friend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    // 친구 관계 고유 식별자 (Auto)
    private long friendRelationId;
    // 기준 유저
    private long userId;
    // 친구 유저
    private long friendId;
    // 친구 맺은 일시
    private String friendCreatedAt;
}

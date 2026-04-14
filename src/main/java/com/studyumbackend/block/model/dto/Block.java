package com.studyumbackend.block.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    // 차단 고유 식별자
    private long blockId;
    // 차단한 유저
    private long blockerUserId;
    // 차단된 유저
    private long blockedUserId;
    // 차단 일시
    private String createdAt;
}

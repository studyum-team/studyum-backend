package com.studyumbackend.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomSearchRequestDTO {
    // 쿼리 파라미터로 전달
    // 채팅방 카테고리 ID
    private String categoryId;
    // 검색 키워드
    private String keyword;
    // 채팅방 모집 상태
    private String status;
}
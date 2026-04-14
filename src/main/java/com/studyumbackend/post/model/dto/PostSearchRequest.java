package com.studyumbackend.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSearchRequest {
    // 검색 키워드
    private String keyword;
    // 대분류
    private Long parentCategoryId;
    // 소분류
    private Long categoryId;
   // 채팅방 모집 상태
    private String chatRoomRecruitStatus;
    // 게시글 상태 (신고여부)
    private String postStatus;
}

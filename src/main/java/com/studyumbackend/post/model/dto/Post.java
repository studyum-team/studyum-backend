package com.studyumbackend.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    // 게시글 고유 식별자 (Auto)
    private Long postId;
    // 작성자
    private Long userId;
    // 카테고리
    private Long categoryId;
    // 연관 채팅방(스터디룸)
    private Long chatRoomId;
    // 게시글 제목
    private String postTitle;
    // 게시글 본문
    private String postContent;
    // 게시글 이미지
    private String postImage;
    // 조회수
    private Integer postViewCount;
    // 게시물 신고 횟수
    private Integer postReportCount;
    // 작성일시
    private String postCreatedAt;
    // 수정일시
    private String postUpdatedAt;
    // 게시글 상태 (신고여부)
    private String postStatus;
}

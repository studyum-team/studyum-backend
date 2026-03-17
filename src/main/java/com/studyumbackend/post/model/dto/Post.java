package com.studyumbackend.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    // 게시글 고유 식별자 (Auto)
    private long postId;
    // 작성자
    private long userId;
    // 카테고리
    private long categoryId;
    // 연관 채팅방(스터디룸)
    private long chatRoomId;
    // 게시글 제목
    private String postTitle;
    // 게시글 본문
    private String postContent;
    // 게시글 이미지
    private String postImage;
    // 조회수
    private int postViewCount;
    // 게시물 신고 횟수
    private int postReportCount;
    // 작성일시
    private String postCreatedAt;
    // 수정일시
    private String postUpdatedAt;
}

package com.studyumbackend.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * v_post_list 뷰 테이블 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostListDTO {
    // 게시글 고유 식별자 (Auto)
    private Long postId;
    // 작성자
    private Long userId;
    // 게시글 제목
    private String postTitle;
    // 게시글 본문
    private String postContent;
    // 조회수
    private Integer postViewCount;
    // 작성일시
    private String postCreatedAt;
    // 닉네임
    private String userNickname;
    // 프로필 이미지 URL
    private String userProfileImage;
    // 카테고리 고유 식별자
    private Long categoryId;
    // 카테고리 이름
    private String categoryName;
    // 부모 카테고리 고유 식별자
    private Long parentCategoryId;
    // 부모 카테고리 이름
    private String parentCategoryName;
    // 연관 채팅방(스터디룸)
    private Long chatRoomId;
    // 채팅방 모집 상태
    private String chatRoomRecruitStatus;
    // 댓글수
    private Long commentCount;
    // 좋아요수
    private Long likeCount;
}

package com.studyumbackend.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * v_post_comment 뷰 테이블 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDTO {
    // 댓글 고유 식별자
    private Long CommentId;
    // 댓글이 달린 게시글
    private Long PostId;
    // 일반 댓글(null) / 대댓글(not null) 구분
    private Long ParentId;
    // 댓글 내용
    private String CommentContent;
    // 작성 일시
    private String CommentCreatedAt;
    // 댓글 작성자
    private Long UserId;
    // 닉네임
    private String userNickname;
    // 프로필 이미지 URL
    private String userProfileImage;
}

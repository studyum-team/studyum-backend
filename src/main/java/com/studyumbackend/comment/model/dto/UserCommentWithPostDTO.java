package com.studyumbackend.comment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentWithPostDTO {
    // 댓글 고유 식별자
    private Long commentId;
    // 댓글 작성자
    private Long userId;
    // 댓글 내용
    private String commentContent;
    // 작성 일시
    private String commentCreatedAt;
    // 댓글이 달린 게시글
    private Long postId;
    // 게시글 제목
    private String postTitle;
}

package com.studyumbackend.comment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    // 댓글 고유 식별자
    private Long commentId;
    // 댓글이 달린 게시글
    private Long postId;
    // 댓글 작성자
    private Long userId;
    // 일반 댓글(null) / 대댓글(not null) 구분
    private Long parentId;
    // 댓글 내용
    private String commentContent;
    // 댓글 신고 횟수
    private Integer commentReportCount;
    // 작성 일시
    private String commentCreatedAt;
    // 댓글 상태 (신고여부)
    private String commentStatus;
}

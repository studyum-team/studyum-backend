package com.studyumbackend.comment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    // 댓글 고유 식별자
    private Long CommentId;
    // 댓글이 달린 게시글
    private Long PostId;
    // 댓글 작성자
    private Long UserId;
    // 일반 댓글(null) / 대댓글(not null) 구분
    private Long ParentId;
    // 댓글 내용
    private String CommentContent;
    // 댓글 신고 횟수
    private int CommentReportCount;
    // 작성 일시
    private String CommentCreatedAt;
}

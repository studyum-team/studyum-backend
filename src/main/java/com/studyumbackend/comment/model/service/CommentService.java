package com.studyumbackend.comment.model.service;

import com.studyumbackend.comment.model.dto.Comment;
import com.studyumbackend.comment.model.dto.PostCommentDTO;
import com.studyumbackend.comment.model.dto.UserCommentWithPostDTO;

import java.util.List;

public interface CommentService {

    // 댓글 작성
    void postComment(Comment comment);

    // 댓글 목록
    List<PostCommentDTO> getCommentByPostId(Long postId);

    // 댓글 수정
    void putComment(Comment comment);

    // 댓글 삭제
    void deleteComment(Comment comment);

    // 댓글 신고
    void putCommentReportCount(Long commentId);

    // 내가 쓴 댓글
    List<UserCommentWithPostDTO> getCommentByUserId(Long userId);
}

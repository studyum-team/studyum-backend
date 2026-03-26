package com.studyumbackend.comment.model.mapper;

import com.studyumbackend.comment.model.dto.Comment;
import com.studyumbackend.comment.model.dto.PostCommentDTO;
import com.studyumbackend.comment.model.dto.UserCommentWithPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 댓글 작성
    int insertComment(Comment comment);

    // 댓글 목록
    List<PostCommentDTO> selectCommentByPostId(Long postId);

    // 댓글 수정
    int updateComment(Comment comment);

    // 댓글 삭제
    int deleteComment(Comment comment);

    // 댓글 신고
    int updateCommentReportCount(Comment comment);

    // 내가 쓴 댓글
    List<UserCommentWithPostDTO> selectCommentByUserId(Long userId);

}

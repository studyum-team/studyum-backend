package com.studyumbackend.comment.model.service;

import com.studyumbackend.comment.model.dto.Comment;
import com.studyumbackend.comment.model.dto.PostCommentDTO;
import com.studyumbackend.comment.model.dto.UserCommentWithPostDTO;
import com.studyumbackend.comment.model.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;

    // 댓글 작성
    @Override
    @Transactional
    public void postComment(Comment comment) {
        int result = commentMapper.insertComment(comment);
        if(result != 1) {
            throw new RuntimeException("댓글 작성에 실패했습니다.");
         }
    }

    // 댓글 목록
    @Override
    @Transactional(readOnly = true)
    public List<PostCommentDTO> getCommentByPostId(Long postId) {
        List<PostCommentDTO> commentList = commentMapper.selectCommentByPostId(postId);
        return commentList;
    }

    // 댓글 수정
    @Override
    @Transactional
    public void putComment(Comment comment) {
        int result = commentMapper.updateComment(comment);
        if(result != 1) {
            throw new RuntimeException("본인 댓글일 경우에만 수정이 가능합니다.");
        }
    }

    // 댓글 삭제
    @Override
    @Transactional
    public void deleteComment(Comment comment) {
        int result = commentMapper.deleteComment(comment);
        if(result != 1) {
            throw new RuntimeException("본인 댓글일 경우에만 삭제가 가능합니다.");
        }
    }

    // 댓글 신고
    @Override
    @Transactional
    public void putCommentReportCount(Long commentId) {
        int result = commentMapper.updateCommentReportCount(commentId);
        if(result != 1) {
            throw new RuntimeException("존재하지 않는 댓글입니다.");
        }
    }

    // 내가 쓴 댓글
    @Override
    @Transactional(readOnly = true)
    public List<UserCommentWithPostDTO> getCommentByUserId(Long userId) {
        List<UserCommentWithPostDTO> commentList = commentMapper.selectCommentByUserId(userId);
        return commentList;
    }
}

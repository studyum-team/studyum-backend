package com.studyumbackend.comment.controller;

import com.studyumbackend.comment.model.dto.Comment;
import com.studyumbackend.comment.model.dto.PostCommentDTO;
import com.studyumbackend.comment.model.dto.UserCommentWithPostDTO;
import com.studyumbackend.comment.model.service.CommentService;
import com.studyumbackend.common.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final AuthUtil authUtil;

    /**
     * 댓글 수정
     * PATCH /api/comments/{commentId}
     *
     * @param commentId 댓글 ID
     * @param comment 수정할 댓글 내용
     * @param token 토큰
     * @return 200 OK
     */
    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> putComment(@PathVariable Long commentId,
                                           @RequestBody Comment comment,
                                           @RequestHeader("Authorization") String token){
        comment.setCommentId(commentId);
        comment.setUserId(authUtil.getCurrentUserId(token));
        log.info("🟢 댓글 수정 commentId={} userId={}",
                commentId,
                comment.getUserId());
        commentService.putComment(comment);
        return ResponseEntity.ok().build();
    }

    /**
     * 댓글 삭제
     * DELETE /api/comments/{commentId}
     *
     * @param commentId 삭제할 댓글 ID
     * @param token 토큰
     * @return 204 No Content
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              @RequestHeader("Authorization") String token) {
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setUserId(authUtil.getCurrentUserId(token));
        log.info("🟢 댓글 삭제 commentId={} userId={}",
                commentId,
                comment.getUserId());
        commentService.deleteComment(comment);
        return ResponseEntity.noContent().build();
    }

    /**
     * 내가 쓴 댓글
     * GET /api/comments/me
     *
     * @param token 토큰
     * @return 내가 쓴 댓글 목록
     */
    @GetMapping("/me")
    public ResponseEntity<List<UserCommentWithPostDTO>> getCommentByUserId(@RequestHeader("Authorization") String token) {
        Long userId = authUtil.getCurrentUserId(token);
        log.info("🟢 내가 쓴 댓글  userId={}",
                userId);
        return ResponseEntity.ok(commentService.getCommentByUserId(userId));
    }
}

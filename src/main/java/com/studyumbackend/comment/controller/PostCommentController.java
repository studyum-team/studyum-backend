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
@RequestMapping("/api/posts/{postId}/comments")
public class PostCommentController {

    private final CommentService commentService;
    private final AuthUtil authUtil;

    /**
     * 댓글 / 대댓글 작성
     * POST /api/posts/{postId}/comments
     *
     * @param comment 작성할 댓글 내용
     * @param token 토큰
     * @return 200 OK
     */
    @PostMapping
    public ResponseEntity<Void> postComment(@PathVariable Long postId,
                                            @RequestBody Comment comment,
                                            @RequestHeader("Authorization") String token) {
        comment.setPostId(postId);
        comment.setUserId(authUtil.getCurrentUserId(token));
        log.info("🟢 댓글 작성 요청 postId={} userId={}",
                postId,
                comment.getUserId());
        commentService.postComment(comment);
        return ResponseEntity.ok().build();
    }

    /**
     * 댓글 목록 조회
     * GET /api/posts/{postId}/comments
     *
     * @param postId 게시글 ID
     * @return 게시글 ID에 해당하는 댓글 목록
     */
    @GetMapping
    public ResponseEntity<List<PostCommentDTO>> getCommentByPostId(@PathVariable Long postId){
        log.info("🟢 댓글 목록 조회 postId={}", postId);
        return ResponseEntity.ok(commentService.getCommentByPostId(postId));
    }
}

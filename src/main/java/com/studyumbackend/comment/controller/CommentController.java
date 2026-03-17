package com.studyumbackend.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    /**
     * 댓글 / 대댓글 작성
     */
    // PostMapping

    /**
     * 댓글 목록 조회
     */
    // GetMapping

    /**
     * 댓글 수정
     */
    // PatchMapping("/{commentId}")

    /**
     * 댓글 삭제
     */
    // PatchMapping("/{commentId}")

    /**
     * 댓글 신고
     */
    // PostMapping("/{commentId}/report")
}

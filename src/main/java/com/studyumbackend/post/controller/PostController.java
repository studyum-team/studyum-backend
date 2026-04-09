package com.studyumbackend.post.controller;

import com.studyumbackend.common.util.AuthUtil;
import com.studyumbackend.post.model.dto.*;
import com.studyumbackend.post.model.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final AuthUtil authUtil;

    /**
     * 게시글 작성
     * POST /api/posts
     *
     * @param post 작성할 게시글 정보
     * @param token 토큰
     * @return 200 OK
     */
    @PostMapping
    public ResponseEntity<Void> postPost(@RequestBody Post post,
                                         @RequestHeader("Authorization") String token) {
        post.setUserId(authUtil.getCurrentUserId(token));
        log.info("🟢 게시글 작성 요청 post={}", post);
        postService.postPost(post);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 목록 조회 + 검색 + 필터
     * GET /api/posts
     *
     * @param postSearchRequest 검색 조건(키워드 + 대분류 + 소분류 + 모집 여부 확인)
     * @return 게시글 목록
     */
    // /api/posts?keyword=스프링&parentCategoryId=1&chatRoomRecruitStatus=RECRUITING
     @GetMapping
     public ResponseEntity<List<PostListDTO>> getPostList(
             @ModelAttribute PostSearchRequest postSearchRequest) {
         log.info("🟢 게시글 목록 조회 keyword={}, parentCategoryId={}, categoryId={}, recruitStatus={}",
                 postSearchRequest.getKeyword(), // 제목 키워드
                 postSearchRequest.getParentCategoryId(), // 대분류 필터
                 postSearchRequest.getCategoryId(), // 소분류 필터
                 postSearchRequest.getChatRoomRecruitStatus()); // 모집 여부 확인
         return ResponseEntity.ok(postService.getPostList(postSearchRequest));
     }

    /**
     * 게시글 상세 조회
     * GET /api/posts/{postId}
     *
     * @param postId 조회할 게시글 ID
     * @return 게시글 상세 정보
     */
     @GetMapping("/{postId}")
    public ResponseEntity<PostDetailDTO> getPostDetail(@PathVariable Long postId) {
         log.info("🟢 게시글 상세 조회 postId={}", postId);
         return ResponseEntity.ok(postService.getPostDetail(postId));
     }

    /**
     * 게시글 수정
     * PATCH /api/posts/{postId}
     *
     * @param postId 수정할 게시글 ID
     * @param post 수정할 내용
     * @param token 토큰
     * @return 200 OK
     */
    @PatchMapping("/{postId}")
    public ResponseEntity<Void> putPost(@PathVariable Long postId,
                                        @RequestBody Post post,
                                        @RequestHeader("Authorization") String token) {
        post.setPostId(postId);
        post.setUserId(authUtil.getCurrentUserId(token));
         log.info("🟢 게시글 수정 postId={} userId={}", postId, post.getUserId());
         postService.putPost(post);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 삭제
     * DELETE /api/posts/{postId}
     *
     * @param postId 삭제할 게시글 ID
     * @param token 토큰
     * @return 204 No Content
     */
    // /api/posts/5?userId=1
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId,
                                               @RequestHeader("Authorization") String token) {
        Post post = new Post();
        post.setPostId(postId);
        post.setUserId(authUtil.getCurrentUserId(token));
        log.info("🟢 게시글 삭제 postId={} userId={}", postId, post.getUserId());
        postService.deletePostById(post);
        return ResponseEntity.noContent().build(); // 204 삭제 성공
    }


    /**
     * 게시글 좋아요
     * POST /api/posts/{postId}/like
     *
     * @param postId 좋아요할 게시글 ID
     * @param token 토큰
     * @return 200 OK
     */
     @PostMapping("/{postId}/like")
     public ResponseEntity<Void> postPostLike(@PathVariable Long postId,
                                              @RequestHeader("Authorization") String token) {
         PostLike postLike = new PostLike();
         postLike.setPostId(postId);
         postLike.setUserId(authUtil.getCurrentUserId(token));
         log.info("🟢 게시글 좋아요 postId={} userId={}", postId, postLike.getUserId());
         postService.postPostLike(postLike);
         return ResponseEntity.ok().build();
     }

    /**
     * 게시글 좋아요 취소
     * DELETE /api/posts/{postId}/like
     *
     * @param postId 좋아요 취소할 게시글 ID
     * @param token 토큰
     * @return 200 OK
     */
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<Void> deletePostLike(@PathVariable Long postId,
                                               @RequestHeader("Authorization") String token) {
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(authUtil.getCurrentUserId(token));
        log.info("🟢 게시글 좋아요 취소 postId={} userId={}", postId, postLike.getUserId());
        postService.deletePostLike(postLike);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 신고
     * POST /api/posts/{postId}/report
     * TODO 신고 테이블 추가? 유저 중복 신고 어떻게 하지..!
     *
     * @param postId 신고할 게시글 ID
     * @return 200 OK
     */
    @PostMapping("/{postId}/report")
    public ResponseEntity<Void> putPostReportCount(@PathVariable Long postId) {
         log.info("🟢 게시글 신고 postId={}", postId);
         postService.putPostReportCount(postId);
         return ResponseEntity.ok().build();
    }

    /**
     * 특정 유저가 쓴 게시글
     * GET /api/posts/user/{userId}
     *
     * @param userId 조회할 유저 ID
     * @return 게시글 목록
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostListDTO>> getPostListByUserId(@PathVariable Long userId) {
        log.info("🟢 특정 유저가 쓴 게시글 userId={}", userId);
        return ResponseEntity.ok(postService.getPostListByUserId(userId));
    }
}

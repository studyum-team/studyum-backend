package com.studyumbackend.post.model.service;

import com.studyumbackend.post.model.dto.*;

import java.util.List;

public interface PostService {
    // 게시글 작성
    void postPost(Post post);

    // 게시글 목록 + 검색
    List<PostListDTO> getPostList(PostSearchRequest postSearchRequest);

    // 게시글 상세 조회 (조회수 증가 포함)
    PostDetailDTO getPostDetail(Long postId);

    // 게시글 수정
    void putPost(Post post);

    // 게시글 삭제
    void deletePostById(Post post);

    // 게시글 좋아요
    void postPostLike(PostLike postLike);

    // 게시글 좋아요 취소
    void deletePostLike(PostLike postLike);

    // 특정 유저가 쓴 게시글
    List<PostListDTO> getPostListByUserId(Long userId);
}

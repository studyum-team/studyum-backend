package com.studyumbackend.post.model.mapper;

import com.studyumbackend.post.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    // 게시글 작성
    int insertPost(Post post);

    // 게시글 목록 + 검색
    List<PostListDTO> selectPostList(PostSearchRequest postSearchRequest);

    // 게시글 상세 조회
    PostDetailDTO selectPostDetailById(Long postId);

    // 게시글 수정
    int updatePost(Post post);

    // 게시글 삭제
    int deletePost(Post post);

    // 게시글 좋아요
    int insertPostLike(PostLike postLike);

    // 게시글 좋아요 취소
    int deletePostLike(PostLike postLike);

    // 게시글 조회수
    int updatePostViewCount(Long postId);

    // 특정 유저가 쓴 게시글
    List<PostListDTO> selectPostListByUserId(Long userId);
}

package com.studyumbackend.post.model.service;

import com.studyumbackend.post.model.dto.*;
import com.studyumbackend.post.model.mapper.PostMapper;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostMapper postMapper;

    // 게시글 작성
    @Override
    @Transactional
    public void postPost(Post post) {
        int result = postMapper.insertPost(post);
        if (result != 1) {
            throw new RuntimeException("게시글 작성에 실패했습니다.");
        }
    }

    // 게시글 목록 + 검색
    @Override
    @Transactional(readOnly = true)
    public List<PostListDTO> getPostList(PostSearchRequest postSearchRequest) {
        List<PostListDTO> postList = postMapper.selectPostList(postSearchRequest);

        return postList;
    }

    // 게시글 상세 조회 (조회수 증가 포함)
    @Override
    @Transactional
    public PostDetailDTO getPostDetail(Long postId) {
        // 조회수 증가(둘이 항상 같이 성공하거나 같이 실패해야 하기 때문에 하나로!)
        postMapper.updatePostViewCount(postId);

        // 상세 조회
        PostDetailDTO postDetail = postMapper.selectPostDetailById(postId);

        return postDetail;
    }

    // 게시글 수정
    @Override
    @Transactional
    public void putPost(Post post) {
        int result = postMapper.updatePost(post);
        if(result != 1){
            throw new RuntimeException("본인 글일 경우에만 수정이 가능합니다.");
        }
    }

    // 게시글 삭제
    @Override
    @Transactional
    public void deletePostById(Post post) {
        int result = postMapper.deletePost(post);
        if(result != 1){
            throw new RuntimeException("본인 글일 경우에만 삭제가 가능합니다.");
        }
    }

    // 게시글 좋아요
    @Override
    @Transactional
    public void postPostLike(PostLike postLike) {
        int result = postMapper.insertPostLike(postLike);
        if (result != 1) {
            throw new RuntimeException("좋아요 처리에 실패했습니다.");
        }
    }

    // 게시글 좋아요 취소
    @Override
    @Transactional
    public void deletePostLike(PostLike postLike) {
        int result = postMapper.deletePostLike(postLike);
        if (result != 1) {
            throw new RuntimeException("좋아요 취소에 실패했습니다.");
        }
    }

    // 특정 유저가 쓴 게시글
    @Override
    @Transactional(readOnly = true)
    public List<PostListDTO> getPostListByUserId(Long userId) {
        List<PostListDTO> postList = postMapper.selectPostListByUserId(userId);
        return postList;
    }
}

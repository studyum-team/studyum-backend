package com.studyumbackend.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {
    // 좋아요 고유 식별자 (Auto)
    private long postLikeId;
    // 좋아요한 게시글
    private long postId;
    // 좋아요를 누른 유저
    private long userId;
    // 좋아요 일시
    private String postLikeCreatedAt;
}

package com.studyumbackend.block.model.service;

import com.studyumbackend.block.model.dto.Block;
import com.studyumbackend.friend.model.dto.Friend;
import com.studyumbackend.friend.model.dto.FriendRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BlockService {

    // 차단 추가
    void postBlock(Long blockerUserId, Long blockedUserId);

    // 차단 삭제
    void deleteBlock(Long blockerUserId, Long blockedUserId);

    // 차단 목록 조회
    List<Long> getBlockList(Long userId);

}

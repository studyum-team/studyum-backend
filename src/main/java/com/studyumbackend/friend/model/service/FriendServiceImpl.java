package com.studyumbackend.friend.model.service;

import com.studyumbackend.common.exception.NotFoundException;
import com.studyumbackend.friend.model.dto.Friend;
import com.studyumbackend.friend.model.dto.FriendRequest;
import com.studyumbackend.friend.model.mapper.FriendMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendMapper friendMapper;

    /**
     * 친구 요청 신청
     * @param requesterId 신청자 ID
     * @param receiverId 수신자 ID
     */
    @Transactional
    @Override
    public void postFriendRequest(Long requesterId, Long receiverId) {
        log.info("💡 친구 요청 신청 시작. requester: {}, receiver: {}", requesterId, receiverId);
        FriendRequest request = new FriendRequest();
        request.setRequesterId(requesterId);
        request.setReceiverId(receiverId);

        int result = friendMapper.insertFriendRequest(request);
        if (result == 0) {
            log.error("❌ 친구 요청 신청 실패. requester: {}", requesterId);
            throw new RuntimeException("친구 요청 신청 중 오류가 발생했습니다.");
        }
        log.info("✅ 친구 요청 신청 완료. requestId: {}", request.getFriendRequestId());
    }

    /**
     * 친구 요청 목록 조회 (수신/신청)
     * @param userId 유저 ID
     * @return 수신/신청 목록을 포함한 Map
     */
    @Override
    public Map<String, List<FriendRequest>> getFriendRequests(Long userId) {
        log.info("💡 친구 요청 전체 목록 조회 시작. userId: {}", userId);
        List<FriendRequest> receivedRequests = friendMapper.selectFriendRequestByReceiverId(userId);
        List<FriendRequest> requestedRequests = friendMapper.selectFriendRequestByRequesterId(userId);

        Map<String, List<FriendRequest>> result = new HashMap<>();
        result.put("received", receivedRequests);
        result.put("requested", requestedRequests);
        log.info("✅ 조회 완료 - 수신: {}건, 신청: {}건", receivedRequests.size(), requestedRequests.size());
        return result;
    }

    /**
     * 친구 요청 취소
     * @param friendRequestId 요청 ID
     */
    @Transactional
    @Override
    public void deleteFriendRequest(Long friendRequestId) {
        log.info("💡 친구 요청 취소 시작. requestId: {}", friendRequestId);
        int result = friendMapper.deleteFriendRequest(friendRequestId);

        if (result == 0) {
            log.warn("⚠️ 친구 요청 취소 실패 (요청 없음). requestId: {}", friendRequestId);
            throw new NotFoundException("취소할 친구 요청을 찾을 수 없습니다.");
        }
        log.info("✅ 친구 요청 취소 완료. requestId: {}", friendRequestId);
    }

    /**
     * 친구 요청 수락/거절 처리
     * @param friendRequest 친구 요청 객체
     * @param status 변경할 상태 (ACCEPTED/REJECTED)
     */
    @Transactional
    @Override
    public void patchFriendRequest(FriendRequest friendRequest, String status) {
        log.info("💡 친구 요청 처리 시작. requestId: {}, status: {}", friendRequest.getFriendRequestId(), status);
        int result;

        if ("ACCEPTED".equals(status)) {
            result = friendMapper.updateFriendRequestStatusToAccepted(friendRequest.getFriendRequestId());
            if (result == 0) throw new RuntimeException("친구 요청 수락 처리 실패");
            log.info("✅ 친구 요청 수락 완료. requestId: {}", friendRequest.getFriendRequestId());
            Friend newFriend = new Friend();
            newFriend.setUserId(friendRequest.getReceiverId());
            newFriend.setFriendId(friendRequest.getRequesterId());
            log.info("💡 친구 목록 추가 시작. user: {}, friend: {}", newFriend.getUserId(), newFriend.getFriendId());
            int result2 = friendMapper.insertFriend(newFriend);
            if (result2 == 0) {
                log.error("❌ 친구 목록 추가 실패.");
                throw new RuntimeException("친구 추가 중 오류가 발생했습니다.");
            }
            log.info("✅ 친구 목록 추가 완료.");
        } else if ("REJECTED".equals(status)) {
            result = friendMapper.updateFriendRequestStatusToRejected(friendRequest.getFriendRequestId());
            if (result == 0) throw new RuntimeException("친구 요청 거절 처리 실패");
            log.info("✅ 친구 요청 거절 완료. requestId: {}", friendRequest.getFriendRequestId());
        }
    }

    /**
     * 친구 목록 조회
     * @param userId 유저 ID
     * @return 친구 목록
     */
    @Override
    public List<Friend> getFriends(Long userId) {
        log.info("💡 친구 목록 조회 시작. userId: {}", userId);
        return friendMapper.selectFriend(userId);
    }

    /**
     * 친구 삭제
     * @param friendRelationId 관계 ID
     */
    @Transactional
    @Override
    public void deleteFriend(Long friendRelationId) {
        log.info("💡 친구 삭제 시작. relationId: {}", friendRelationId);
        int result = friendMapper.deleteFriend(friendRelationId);

        if (result == 0) {
            log.warn("⚠️ 친구 삭제 실패 (관계 없음). relationId: {}", friendRelationId);
            throw new NotFoundException("삭제할 친구 관계를 찾을 수 없습니다.");
        }
        log.info("✅ 친구 삭제 완료. relationId: {}", friendRelationId);
    }

    @Override
    public FriendRequest getFriendRequestStatus(Long userId, Long friendId) {
        // 보낸 요청이나 받은 요청 중 현재 상태를 확인하는 로직 (필요 시 매퍼 추가 구현)
        return null;
    }
}
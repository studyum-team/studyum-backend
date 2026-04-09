package com.studyumbackend.friend.model.mapper;

import com.studyumbackend.friend.model.dto.Friend;
import com.studyumbackend.friend.model.dto.FriendRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper {

    // 친구목록 추가
    int insertFriend(Friend friend);

    // 친구목록 조회
    List<Friend> selectFriend(Long userId);

    // 친구목록 삭제
    int deleteFriend(Long friendRelationId);

    // 친구 요청 신청
    int insertFriendRequest(FriendRequest friendRequest);

    // 친구 요청 신청 취소
    int deleteFriendRequest(Long friendRequestId);

    // 친구 요청 신청 수락
    int updateFriendRequestStatusToAccepted(Long friendRequestId);

    // 친구 요청 신청 거절
    int updateFriendRequestStatusToRejected(Long friendRequestId);

    // 친구 요청 신청 목록 조회
    List<FriendRequest> selectFriendRequestByRequesterId(Long requesterId);

    // 친구 요청 수신 목록 조회
    List<FriendRequest> selectFriendRequestByReceiverId(Long receiverId);
}
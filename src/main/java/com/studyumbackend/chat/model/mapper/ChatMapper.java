package com.studyumbackend.chat.model.mapper;

import com.studyumbackend.category.model.dto.CategoryResponse;
import com.studyumbackend.chat.model.dto.chatroom.ChatRoom;
import com.studyumbackend.chat.model.dto.chatroom.ChatRoomInfoChangeRequest;
import com.studyumbackend.chat.model.dto.chatroom.ChatRoomListDTO;
import com.studyumbackend.chat.model.dto.chatroom.ChatRoomSearchRequest;
import com.studyumbackend.chat.model.dto.chatroomuser.ChatRoomMemberResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {

    /// ====================
    ///      chatRoom
    /// ====================
    // 채팅방 생성
    int insertChatRoom(ChatRoom chatRoom);

    // 채팅방 목록 검색 : 카테고리/검색어
    ChatRoomSearchRequest selectChatRoomList(ChatRoomSearchRequest request);

    // 본인 채팅방 목록 조회
    ChatRoomListDTO selectChatRoomListByUserId(Long userId);

    // 채팅방 상세조회
    // TODO: Service 레이어에서 ChatRoomDetailResponse에 set(조립)
    // 1. 채팅방 기본 정보 조회
    ChatRoom findChatRoomById(Long chatRoomId);
    // 2. 대분류-소분류 카테고리 조회
    CategoryResponse findCategoryWithParent(Long categoryId);

    // 채팅방 정보 수정
    // TODO: Service 쪽에서 유저 권한 검증 로직 추가 (OWNER만 수정 가능)
    int updateChatRoomInfoById(ChatRoomInfoChangeRequest request, Long chatRoomId);

    // 채팅방 삭제 (소프트 삭제)
    // TODO: Service 쪽에서 유저 권한 검증 로직 추가 (OWNER만 삭제 가능)
    int deleteChatRoom(Long chatRoomId);

    // 유저 권한 검증
    String selectUserRoleByChatRoomId(Long chatRoomId, Long userId);

    /// ====================
    ///     chatRoomUser
    /// ====================
    // 채팅방 멤버 목록 조회
    // TODO: Service 레이어에서 ChatRoomMemberListResponse에 set(조립) - total 데이터 (리스트.size() 사용)
    List<ChatRoomMemberResponse> selectChatRoomUserListByChatRoomId(Long chatRoomId);

    // 멤버 역할 변경
    // TODO: Service 쪽에서 유저 권한 검증 로직 추가 (OWNER만 변경 가능)
    int updateChatRoomUserRole(String chatRoomUserRole, Long userId);

    // 멤버 강퇴
    // TODO: Service 쪽에서 유저 권한 검증 로직 추가 (OWNER만 강퇴 가능)
    int deleteUserFromChatRoom(Long chatRoomUserId);

    // 채팅방 나가기 (본인)
    // TODO: 나가는 본인이 OWNER인 경우 스터디룸 삭제 같이 작동 (유저 권한 검증 로직 추가 필요)
    int deleteMyselfFromChatRoom(Long userId, Long chatRoomId);

    /// ====================
    ///    chatRoomInvite
    /// ====================
    // 유저가 채팅방 직접 신청


    // 유저의 채팅방 신청 목록


    // 채팅방에서 멤버 초대


    // 채팅방에서 멤버 초대/신청 확인 목록


    // 채팅방에서 유저의 신청 수락/거절


    // 유저가 채팅방 초대 수락/거절 (피초대자 본인)



    /// ====================
    ///     chatMessage
    /// ====================
    // 메세지 목록 (커서 페이징)


    // 메세지 전송 (REST fallback)


    // 메세지 삭제 (소프트)


    // 메세지 읽음 처리


}

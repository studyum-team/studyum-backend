package com.studyumbackend.chat.model.mapper;

import com.studyumbackend.category.model.dto.CategoryResponse;
import com.studyumbackend.chat.model.dto.chatroom.ChatRoom;
import com.studyumbackend.chat.model.dto.chatroom.ChatRoomInfoChangeRequest;
import com.studyumbackend.chat.model.dto.chatroom.ChatRoomListDTO;
import com.studyumbackend.chat.model.dto.chatroom.ChatRoomSearchRequest;
import org.apache.ibatis.annotations.Mapper;

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
    int updateChatRoomInfoById(ChatRoomInfoChangeRequest request, Long chatRoomId);

    // 채팅방 삭제 (소프트 삭제)
    int deleteChatRoom(Long chatRoomId);


    /// ====================
    ///     chatRoomUser
    /// ====================
    // 채팅방 멤버 목록 조회


    // 멤버 역할 변경


    // 멤버 강퇴


    // 채팅방 나가기 (본인)


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

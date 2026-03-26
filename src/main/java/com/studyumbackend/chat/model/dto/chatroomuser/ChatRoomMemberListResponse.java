package com.studyumbackend.chat.model.dto.chatroomuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 채팅방 멤버 목록 응답 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomMemberListResponse {
    private int total;
    private List<ChatRoomMemberResponse> data;
}

package com.studyumbackend.chat.model.dto.chatroom;

import com.studyumbackend.category.model.dto.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 채팅방 상세조회 응답 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDetailResponse {
    private Long chatRoomId;
    private CategoryResponse category;
    private String chatRoomName;
    private String chatRoomDescription;
    private String chatRoomImage;
    private String chatRoomVisibility;
    private Integer chatRoomMaxMember;
    private Integer chatRoomCurrentMember;
    private String chatRoomRecruitStatus;
    private String chatRoomCreatedAt;
}

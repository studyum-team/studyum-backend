package com.studyumbackend.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    // 신고 고유 식별자
    private long reportId;

    // 신고자 ID
    private long reporterId;

    // 신고 대상 구분 (POST, COMMENT, USER, CHAT_ROOM)
    // 추후 채팅 메세지 신고 추가하기
    private String targetType;

    // 신고 대상 PK (게시글ID, 댓글ID 등)
    private long targetId;

    // 신고 사유 카테고리 (BAD_WORD, SPAM, SCAM, ETC)
    private String reportCategory;

    // 신고 상세 내용 (ETC의 경우 필수)
    private String reportReason;

    // 신고 접수 상태 (Default: PENDING)
    private String reportStatus;

    // 신고 접수 일시
    private String createdAt;
}
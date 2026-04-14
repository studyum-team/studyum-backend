package com.studyumbackend.report.model.service;

//import com.studyumbackend.chat.model.mapper.ChatRoomMapper;
import com.studyumbackend.comment.model.mapper.CommentMapper;
import com.studyumbackend.post.model.mapper.PostMapper;
import com.studyumbackend.report.model.dto.Report;
import com.studyumbackend.report.model.mapper.ReportMapper;
import com.studyumbackend.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportMapper    reportMapper;
//    private final UserMapper      userMapper;
//    private final ChatRoomMapper  chatRoomMapper;
//    private final PostMapper      postMapper;
//    private final CommentMapper   commentMapper;


    /**
     * 신고 접수
     * @param report 신고 객체
     */
    @Override
    @Transactional
    public void postReport(Report report) {
        // 신고 중복 확인
        int duplicateCount = reportMapper.selectDuplicateReport(report);
        if (duplicateCount > 0) {
            throw new RuntimeException("이미 신고한 대상입니다.");
        }

        // 신고 접수
        report.setReportStatus("PENDING");
        int result = reportMapper.insertReport(report);
        if (result == 0) {
            throw new RuntimeException("신고 등록에 실패했습니다.");
        }
    }

    /**
     * 신고 목록 조회
     * @return 접수된 신고 목록
     */
    @Override
    public List<Report> getReportList() {
        return reportMapper.selectReport();
    }

    /**
     * 신고 상세 조회
     * @param reportId 신고 ID
     * @return
     */
    @Override
    public Report getReportById(Long reportId) {
        Report report = reportMapper.selectReportById(reportId);
        if (report == null) {
            throw new RuntimeException("존재하지 않는 신고입니다. reportId=" + reportId);
        }
        return report;
    }

    /**
     * 신고 승인
     * @param reportId 신고 ID
     */
    @Override
    @Transactional
    public void updateReport(Long reportId, String reportStatus) {
        // 신고 존재 여부 확인
        Report report = reportMapper.selectReportById(reportId);
        if (report == null) {
            throw new RuntimeException("존재하지 않는 신고입니다. reportId=" + reportId);
        }

        // 신고 중복 처리 방지
        if (!"PENDING".equals(report.getReportStatus())) {
            throw new RuntimeException("이미 처리된 신고입니다. reportId=" + reportId);
        }

        // 신고 승인
        int result = reportMapper.updateReport(reportId, reportStatus);
        if (result == 0) {
            throw new RuntimeException("신고 상태 변경에 실패했습니다. reportId=" + reportId);
        }

        // 타겟 원본 SUSPENDED 처리
        // suspendTarget(report.getTargetType(), report.getTargetId());
    }

//    // ──────────────────────────────────────────────
//    // 내부 메서드: 타겟 타입별 SUSPENDED 분기
//    // ──────────────────────────────────────────────
//    private void suspendTarget(String targetType, Long targetId) {
//        switch (targetType) {
//            case "USER" -> {
//                int result = userMapper.suspendUser(targetId);
//                if (result == 0) {
//                    throw new RuntimeException("유저 정지 처리에 실패했습니다. userId=" + targetId);
//                }
//                log.info("[신고 승인] 유저 정지 처리 완료. userId={}", targetId);
//            }
//            case "CHAT_ROOM" -> {
//                int result = chatRoomMapper.suspendChatRoom(targetId);
//                if (result == 0) {
//                    throw new RuntimeException("채팅방 정지 처리에 실패했습니다. chatRoomId=" + targetId);
//                }
//                log.info("[신고 승인] 채팅방 정지 처리 완료. chatRoomId={}", targetId);
//            }
//            case "POST" -> {
//                int result = postMapper.suspendPost(targetId);
//                if (result == 0) {
//                    throw new RuntimeException("게시글 정지 처리에 실패했습니다. postId=" + targetId);
//                }
//                log.info("[신고 승인] 게시글 정지 처리 완료. postId={}", targetId);
//            }
//            case "COMMENT" -> {
//                int result = commentMapper.suspendComment(targetId);
//                if (result == 0) {
//                    throw new RuntimeException("댓글 정지 처리에 실패했습니다. commentId=" + targetId);
//                }
//                log.info("[신고 승인] 댓글 정지 처리 완료. commentId={}", targetId);
//            }
//            default -> throw new IllegalArgumentException("지원하지 않는 신고 타입입니다. targetType=" + targetType);
//        }
//    }
}
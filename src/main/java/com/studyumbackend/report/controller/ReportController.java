package com.studyumbackend.report.controller;

import com.studyumbackend.common.util.AuthUtil;
import com.studyumbackend.report.model.dto.Report;
import com.studyumbackend.report.model.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

    private final AuthUtil authUtil;
    private final ReportService reportService;

    // ==========================================
    //              Report Management
    // ==========================================

    /**
     * 신고 목록 조회 (관리자)
     * @param authHeader JWT 토큰 헤더
     * @return 신고 목록 + 상태코드 200
     */
    @GetMapping
    public ResponseEntity<List<Report>> getReportList(@RequestHeader("Authorization") String authHeader) {
        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        log.info("신고 목록 조회 요청 - 유저 ID: {}", currentUserId);

        List<Report> reportList = reportService.getReportList();
        return ResponseEntity.ok(reportList);
    }

    /**
     * 신고 상세 조회 (관리자)
     * @param authHeader JWT 토큰 헤더
     * @param reportId   조회할 신고 ID
     * @return 신고 상세 정보 + 상태코드 200
     */
    @GetMapping("/{reportId}")
    public ResponseEntity<Report> getReportById(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long reportId) {

        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        log.info("신고 상세 조회 요청 - 유저 ID: {}, 신고 ID: {}", currentUserId, reportId);

        Report report = reportService.getReportById(reportId);
        return ResponseEntity.ok(report);
    }

    /**
     * 신고 접수
     * @param authHeader JWT 토큰 헤더
     * @param report     신고 객체
     * @return 상태코드 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Void> postReport(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Report report) {

        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        log.info("신고 접수 요청 - 유저 ID: {}, 대상 타입: {}, 대상 ID: {}",
                currentUserId, report.getTargetType(), report.getTargetId());

        report.setReporterId(currentUserId);
        reportService.postReport(report);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 신고 상태 변경 (관리자)
     * @param authHeader   JWT 토큰 헤더
     * @param reportId     처리할 신고 ID
     * @param reportStatus 변경할 상태 (ACCEPTED / REJECTED)
     * @return 상태코드 200
     */
    @PatchMapping("/{reportId}")
    public ResponseEntity<Void> updateReport(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long reportId,
            @RequestParam String reportStatus) {

        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        log.info("신고 상태 변경 요청 - 관리자 ID: {}, 신고 ID: {}, 변경 상태: {}",
                currentUserId, reportId, reportStatus);

        reportService.updateReport(reportId, reportStatus);
        return ResponseEntity.ok().build();
    }
}
package com.studyumbackend.report.model.service;

import com.studyumbackend.report.model.dto.Report;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {

    // 신고 접수
    void postReport(Report report);

    // 신고 조회
    List<Report> getReportList();

    // 신고 상세 조회
    Report getReportById(Long reportId);

    // 신고 승인
    void updateReport(Long reportId, String reportStatus);

}

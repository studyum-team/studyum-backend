package com.studyumbackend.report.model.mapper;

import com.studyumbackend.report.model.dto.Report;
import com.studyumbackend.user.model.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface ReportMapper {

    // 신고 추가
    int insertReport(Report report);

    // 신고 중복 확인
    int selectDuplicateReport(Report report);

    // 신고 조회
    List<Report> selectReport();

    // 신고 상세 조회
    Report selectReportById(Long reportId);

    // 신고 상태 변경
    int updateReport(Long reportId, String reportStatus);

}

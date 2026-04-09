package com.studyumbackend.user.model.mapper;

import com.studyumbackend.user.model.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface UserMapper {

    // 회원가입
    int insertUser(User user);

    // 이메일 중복확인
    String selectExistedEmail(String userEmail);

    // 연락처 중복확인
    String selectExistedPhone(String userPhone);

    // 닉네임 중복확인
    String selectExistedNickname(String userNickname);

    // 로그인
    User login(String userEmail);

    // 로그아웃

    // 회원정보 조회
    User selectUser(Long userId);

    // 마이페이지 활동량 조회
    List<LocalDate> selectUserDailyActivity(Long userId);

    // 마이페이지 활동량 추가
    int insertUserDailyActivity(Long userId);

    // 회원정보 수정
    int updateUser(User user);

    // 회원탈퇴
    int deleteUser(Long userId);
}

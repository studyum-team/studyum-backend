package com.studyumbackend.user.model.service;

import com.studyumbackend.user.model.dto.User;
import com.studyumbackend.user.model.dto.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface UserService {

    // 회원가입
    void signup(User user);

    // 이메일 중복확인
    boolean existsByUserEmail(String userEmail);

    // 연락처 중복확인
    boolean existsByUserPhone(String userPhone);

    // 닉네임 중복확인
    boolean existsByUserNickname(String userNickname);

    // 로그인
    User login(String userEmail, String userPassword);

    // 로그아웃
    void logout();

    // 회원정보 조회
    User getUserInfoById(Long userId);

    // 회원정보 수정
    void patchUserInfo(UserUpdateRequest userUpdateRequest, Long currentUserId);

    // 회원정보 삭제
    void deleteUser(Long userId);

    // 마이페이지 활동량 조회
    List<LocalDate> getUserActivity(Long userId);

    // 마이페이지 활동량 추가
    void postUserActivity(Long userId);
}

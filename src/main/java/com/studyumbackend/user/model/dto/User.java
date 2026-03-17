package com.studyumbackend.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 회원 고유 식별자
    private Long userId;
    // 로그인용 이메일
    private String userEmail;
    // 암호화된 비밀번호
    private String userPassword;
    // 닉네임
    private String userName;
    // 연락처
    private String userPhone;
    // 생년월일
    private String userBirthDate;
    // 프로필 이미지 URL
    private String userProfileImage;
    // 자기소개
    private String userBio;
    // 가입 일시
    private String userCreatedAt;
    // 정보 수정 일시
    private String userUpdatedAt;
}

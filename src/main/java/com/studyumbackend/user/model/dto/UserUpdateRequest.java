package com.studyumbackend.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    // 회원 고유 식별자
    private Long userId;
    // 닉네임
    private String userNickname;
    // 실제 파일 수신용
    private MultipartFile profileFile;
    // 프로필 이미지 URL
    private String userProfileImage;
    // 자기소개
    private String userBio;
}

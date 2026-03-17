package com.studyumbackend.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    /**
     * 본인 정보 조회
     */
    // @GetMapping("/me")

    /**
     * 특정 유저 정보 조회
     */
    // @GetMapping("/{userId}")

    /**
     * 본인 정보 수정
     */
    // @PatchMapping("/me")

    /**
     * 본인 프로필 이미지 업로드 요청
     */
    // @PatchMapping("/me/profile-image")

    /**
     * Cloudinary 프로필 업로드
     */
    // @PostMapping("/upload")
    // https://api.cloudinary.com/v1_1/{cloud_name}/image/upload

    /**
     * 회원 탈퇴
     */
    // @DeleteMapping("/me")
}


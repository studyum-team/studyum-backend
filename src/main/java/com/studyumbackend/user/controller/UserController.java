package com.studyumbackend.user.controller;

import com.studyumbackend.common.util.AuthUtil;
import com.studyumbackend.user.model.dto.User;
import com.studyumbackend.user.model.dto.UserUpdateRequest;
import com.studyumbackend.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final AuthUtil authUtil;
    private final UserService userService;

    // ==========================================
    //                User Information
    // ==========================================

    /**
     * 본인 정보 조회
     * @param authHeader JWT 토큰 헤더
     * @return 유저 객체 + 상태코드 200
     */
    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        User user = userService.getUserInfoById(currentUserId);
        return ResponseEntity.ok(user);
    }

    /**
     * 특정 회원정보 조회
     * @param userId 유저 ID
     * @return 유저 객체 + 상태코드 200
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserInfoById(@PathVariable Long userId) {
        User user = userService.getUserInfoById(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * 회원정보 수정
     * @param authHeader JWT 토큰 헤더
     * @param userUpdateRequest 수정사항 객체
     * @return 상태코드 200
     */
    @PatchMapping("/me")
    public ResponseEntity<Void> patchUserInfo(@RequestHeader("Authorization") String authHeader,
                                              @ModelAttribute UserUpdateRequest userUpdateRequest) {
        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        userService.patchUserInfo(userUpdateRequest, currentUserId);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 탈퇴
     * @param userId 유저 ID
     * @return 상태코드 204
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 본인 프로필 이미지 업로드 요청
     */
    // @PatchMapping("/me/profile-image")

    /**
     * Cloudinary 프로필 업로드
     */
    // @PostMapping("/upload")
    // https://api.cloudinary.com/v1_1/{cloud_name}/image/upload




    // ==========================================
    //                User Activity
    // ==========================================

    /**
     * 활동량 조회
     * @param userId 유저 ID
     * @return 활동 날짜 리스트 + 상태코드 200
     */
    @GetMapping("/activity/{userId}")
    public ResponseEntity<List<LocalDate>> getUserActivity(@PathVariable Long userId) {
        List<LocalDate> result = userService.getUserActivity(userId);
        return ResponseEntity.ok(result);
    }

    /**
     * 활동량 추가
     * @param userId 유저 ID
     * @return 상태코드 200
     */
    @PostMapping("/activity/{userId}")
    public ResponseEntity<Void> postUserActivity(@PathVariable Long userId) {
        userService.postUserActivity(userId);
        return ResponseEntity.ok().build();
    }
}


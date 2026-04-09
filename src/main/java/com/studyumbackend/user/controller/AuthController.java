package com.studyumbackend.user.controller;

import com.studyumbackend.common.util.JwtUtil;
import com.studyumbackend.user.model.dto.LoginRequest;
import com.studyumbackend.user.model.dto.LoginResponse;
import com.studyumbackend.user.model.dto.User;
import com.studyumbackend.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // ==========================================
    //               Signup / Login
    // ==========================================

    /**
     * 회원가입
     * @param user 유저 객체
     * @return 상태코드 200
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user) {
        userService.signup(user);
        return ResponseEntity.ok().build();
    }

    /**
     * 이메일 중복확인
     * @param userEmail 유저가 작성한 이메일
     * @return 상태코드 200
     */
    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkEmail(@RequestParam("email") String userEmail) {
        return ResponseEntity.ok(!userService.existsByUserEmail(userEmail));
    }

    /**
     * 연락처 중복확인
     * @param userPhone 유저가 작성한 연락처
     * @return 상태코드 200
     */
    @GetMapping("/checkPhone")
    public ResponseEntity<Boolean> checkPhone(@RequestParam("phone") String userPhone) {
        return ResponseEntity.ok(!userService.existsByUserPhone(userPhone));
    }

    /**
     * 닉네임 중복확인
     * @param userNickname 유저가 작성한 닉네임
     * @return 상태코드 200
     */
    @GetMapping("/checkNickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam("nickname") String userNickname) {
        return ResponseEntity.ok(!userService.existsByUserNickname(userNickname));
    }

    /**
     * 로그인
     * @param loginRequest 로그인 요청 DTO
     * @return 로그인 응답 DTO + 상태코드 200
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.getUserEmail(), loginRequest.getUserPassword());

        String token = jwtUtil.generateToken(user.getUserId(), user.getUserEmail());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);

        log.info("✅ 토큰 생성 완료 - 이메일 : {}", user.getUserEmail());
        return ResponseEntity.ok(loginResponse);
    }

    // 로그아웃

}

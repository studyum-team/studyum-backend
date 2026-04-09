package com.studyumbackend.user.model.service;

import com.studyumbackend.common.exception.BadRequestException;
import com.studyumbackend.common.exception.NotFoundException;
import com.studyumbackend.common.util.ValidateUtil;
import com.studyumbackend.user.model.dto.User;
import com.studyumbackend.user.model.dto.UserUpdateRequest;
import com.studyumbackend.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    // private final FileUploadService fileUploadService;
    private final ValidateUtil validateUtil;

    /**
     * 회원가입
     * @param user 유저 객체
     */
    @Transactional
    @Override
    public void signup(User user) {

        // 중복확인
        String existingEmail = userMapper.selectExistedEmail(user.getUserEmail());
        String existingPhone = userMapper.selectExistedPhone(user.getUserPhone());
        String existingNickname = userMapper.selectExistedNickname(user.getUserNickname());

        if (existingEmail != null) {
            log.warn("❌ 이미 존재하는 이메일 : {}", existingEmail);
            throw new NotFoundException("이미 존재하는 이메일입니다.");
        }

        if (existingPhone != null) {
            log.warn("❌ 이미 존재하는 연락처 : {}", existingPhone);
            throw new NotFoundException("이미 존재하는 연락처입니다.");
        }

        if (existingNickname != null) {
            log.warn("❌ 이미 존재하는 닉네임 : {}", existingNickname);
            throw new NotFoundException("이미 존재하는 닉네임입니다.");
        }

        // 정규식 검증
        validateUtil.validateEmail(user.getUserEmail());
        validateUtil.validatePassword(user.getUserPassword());
        validateUtil.validateName(user.getUserName());
        validateUtil.validateName(user.getUserNickname());
        validateUtil.validatePhone(user.getUserPhone());

        String encodePassword = bCryptPasswordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encodePassword);

        int result = userMapper.insertUser(user);
        if (result == 0) {
            log.error("❌ 회원가입 DB 삽입 실패 - 이메일: {}", user.getUserEmail());
            throw new RuntimeException("회원가입 중 오류가 발생했습니다.");
        }
        log.info("✅ 회원가입 완료 - 이메일 {}, 사용자명 : {}", user.getUserEmail(), user.getUserName());
    }

    /**
     * 이메일 중복확인
     * @param userEmail 유저 이메일
     * @return  중복 여부
     */
    @Override
    public boolean existsByUserEmail(String userEmail) {
        String result = userMapper.selectExistedEmail(userEmail);
        return result != null;
    }

    /**
     * 연락처 중복확인
     * @param userPhone 유저 연락처
     * @return  중복 여부
     */
    @Override
    public boolean existsByUserPhone(String userPhone) {
        String result = userMapper.selectExistedPhone(userPhone);
        return result != null;
    }

    /**
     * 닉네임 중복확인
     * @param userNickname 유저 닉네임
     * @return  중복 여부
     */
    @Override
    public boolean existsByUserNickname(String userNickname) {
        String result = userMapper.selectExistedNickname(userNickname);
        return result != null;
    }

    /**
     * 로그인
     * @param userEmail     로그인 할 유저 이메일
     * @param userPassword  로그인 할 유저 비밀번호
     * @return 유저 객체
     */
    @Override
    public User login(String userEmail, String userPassword) {
        User user = userMapper.login(userEmail);

        // 비밀번호 검증
        if (bCryptPasswordEncoder.matches(userEmail, user.getUserPassword())) {
            log.info("✅ 로그인 성공 - 이메일 : {}", userEmail);
            return user;
        }
        throw new NotFoundException("로그인 정보 없음");
    }

    // 로그아웃
    @Override
    public void logout() {
        // 세션 방식이라면 SecurityContextHolder나 Session 무효화 로직이 컨트롤러에서 호출됨
        // 토큰 방식이라면 Redis에 Refresh Token 삭제 등의 로직을 여기서 수행
        log.info("💡 로그아웃 로직 수행 (토큰 무효화 또는 세션 처리)");
    }

    /**
     * 회원정보 조회
     * @param userId 유저 ID
     * @return 유저 객체
     */
    @Override
    public User getUserInfoById(Long userId) {
        log.info("💡 회원정보 조회 시작. userId: {}", userId);
        User user = userMapper.selectUser(userId);

        if (user == null) {
            log.warn("⚠️ 조회 결과 - 사용자 없음. userId: {}", userId);
            throw new BadRequestException("사용자를 찾을 수 없습니다.");
        }

        log.info("✅ 회원정보 조회 성공. userId: {}", user.getUserId());
        return user;
    }

    /**
     * 회원정보 수정
     * @param userUpdateRequest 수정 유저 정보 객체
     * @param currentUserId 현재 로그인한 유저 ID
     */
    @Transactional
    @Override
    public void patchUserInfo(UserUpdateRequest userUpdateRequest, Long currentUserId) {
        log.info("💡 회원정보 수정 시작. userId: {}", currentUserId);

        User user = userMapper.selectUser(currentUserId);
        if (!Objects.equals(user.getUserNickname(), userUpdateRequest.getUserNickname())) {
            validateUtil.validateName(userUpdateRequest.getUserNickname());
            log.info("💡 새로운 닉네임 : {}", userUpdateRequest.getUserNickname());
            user.setUserNickname(userUpdateRequest.getUserNickname());
        }

        if (!Objects.equals(user.getUserBio(), userUpdateRequest.getUserBio())) {
            log.info("💡 새로운 자기소개 : {}", userUpdateRequest.getUserBio());
            user.setUserBio(userUpdateRequest.getUserBio());
        }

        // 파일 처리 (MultipartFile -> String 경로)
        MultipartFile file = userUpdateRequest.getProfileFile();
        if (file != null && !file.isEmpty()) {
            // 실제 저장 경로 반환
//            String saveImagePath = fileUploadService.uploadProfileImage(file);
//            user.setUserProfileImage(saveImagePath);
        } else {
            // 새 파일이 없으면 기존 경로 유지
            user.setUserProfileImage(user.getUserProfileImage());
        }

        int result = userMapper.updateUser(user);
        if (result == 0) {
            log.error("❌ 회원정보 수정 실패. userId: {}", currentUserId);
            throw new RuntimeException("회원정보 수정 중 오류가 발생했습니다.");
        }
        log.info("✅ 회원정보 수정 완료. userId: {}", currentUserId);
    }

    /**
     * 회원정보 삭제
     * @param userId 유저 ID
     */
    @Transactional
    @Override
    public void deleteUser(Long userId) {
        log.info("💡 회원정보 삭제 시작. userId: {}", userId);
        int result = userMapper.deleteUser(userId);
        if (result == 0) {
            log.warn("⚠️ 회원탈퇴 실패 (사용자 없음). userId: {}", userId);
            throw new NotFoundException("탈퇴 처리할 사용자를 찾을 수 없습니다.");
        }
        log.info("✅ 회원정보 삭제 성공. userId: {}", userId);
    }

    /**
     * 마이페이지 활동량 조회
     * @param userId    유저 ID
     * @return  활동량(날짜)
     */
    @Override
    public List<LocalDate> getUserActivity(Long userId) {
        log.info("💡 활동량 조회 시작. userId: {}", userId);
        // 매퍼의 반환값에 따라 List<String> 또는 List<LocalDate>로 처리
        return userMapper.selectUserDailyActivity(userId);
    }

    /**
     * 마이페이지 활동량 추가
     * @param userId    유저 ID
     */
    @Transactional
    @Override
    public void postUserActivity(Long userId) {
        log.info("💡 활동량 추가. userId: {}", userId);
        int result = userMapper.insertUserDailyActivity(userId);
        if (result == 0) {
            log.error("❌ 활동량 등록 실패. userId: {}", userId);
            throw new RuntimeException("활동량 등록에 실패했습니다.");
        }
        log.info("✅ 활동량 등록 완료. userId: {}", userId);
    }
}

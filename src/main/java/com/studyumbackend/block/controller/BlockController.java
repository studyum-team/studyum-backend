package com.studyumbackend.block.controller;

import com.studyumbackend.block.model.dto.Block;
import com.studyumbackend.block.model.service.BlockService;
import com.studyumbackend.common.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/block")
public class BlockController {

    private final AuthUtil authUtil;
    private final BlockService blockService;

    // ==========================================
    //              Block Management
    // ==========================================

    /**
     * 차단 목록 조회
     * @param authHeader JWT 토큰 헤더
     * @return 차단된 유저 ID 목록 + 상태코드 200
     */
    @GetMapping
    public ResponseEntity<List<Long>> getBlockList(@RequestHeader("Authorization") String authHeader) {
        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        log.info("차단 목록 조회 요청 - 유저 ID: {}", currentUserId);

        List<Long> blockedUserList = blockService.getBlockList(currentUserId);
        return ResponseEntity.ok(blockedUserList);
    }

    /**
     * 차단 등록
     * @param authHeader JWT 토큰 헤더
     * @param blockedUserId 차단할 대상 유저 ID
     * @return 상태코드 201 (Created)
     */
    @PostMapping("/{blockedUserId}")
    public ResponseEntity<Void> postBlock(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long blockedUserId) {

        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        log.info("유저 차단 요청: {} -> {}", currentUserId, blockedUserId);

        blockService.postBlock(currentUserId, blockedUserId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 차단 해제 (삭제)
     * @param authHeader JWT 토큰 헤더
     * @param blockedUserId 차단 해제할 대상 유저 ID
     * @return 상태코드 204 (No Content)
     */
    @DeleteMapping("/{blockedUserId}")
    public ResponseEntity<Void> deleteBlock(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long blockedUserId) {

        Long currentUserId = authUtil.getCurrentUserId(authHeader);
        log.info("차단 해제 요청: {} -> {}", currentUserId, blockedUserId);

        blockService.deleteBlock(currentUserId, blockedUserId);
        return ResponseEntity.noContent().build();
    }
}
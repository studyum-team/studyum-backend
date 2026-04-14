package com.studyumbackend.block.model.service;

import com.studyumbackend.block.model.dto.Block;
import com.studyumbackend.block.model.mapper.BlockMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {

    private final BlockMapper blockMapper;

    /**
     * 차단 추가
     * @param blockerUserId 차단하는 유저 ID
     * @param blockedUserId 차단되는 유저 ID
     */
    @Override
    @Transactional
    public void postBlock(Long blockerUserId, Long blockedUserId) {

        // 본인 차단 여부 확인
        if (blockerUserId.equals(blockedUserId)) {
            throw new IllegalArgumentException("자기 자신을 차단할 수 없습니다.");
        }

        // 중복 차단 방지
        if (blockMapper.existsBlock(blockerUserId, blockedUserId)) {
            log.warn("이미 차단된 유저입니다: blocker={}, blocked={}", blockerUserId, blockedUserId);
            return;
        }

        Block block = new Block();
        block.setBlockerUserId(blockerUserId);
        block.setBlockedUserId(blockedUserId);

        blockMapper.insertBlock(block);
        log.info("유저 차단 성공: {} -> {}", blockerUserId, blockedUserId);
    }

    /**
     * 차단 삭제
     * @param blockerUserId 차단한 유저 ID
     * @param blockedUserId 차단된 유저 ID
     */
    @Override
    @Transactional
    public void deleteBlock(Long blockerUserId, Long blockedUserId) {
        int result = blockMapper.deleteBlock(blockerUserId, blockedUserId);

        if (result == 0) {
            log.warn("삭제할 차단 내역이 없습니다: {} -> {}", blockerUserId, blockedUserId);
        } else {
            log.info("차단 해제 완료: {} -> {}", blockerUserId, blockedUserId);
        }
    }

    /**
     * 차단 목록 조회
     * @param userId 차단한 유저 ID
     * @return 차단된 유저 ID 목록
     */
    @Override
    @Transactional(readOnly = true)
    public List<Long> getBlockList(Long userId) {
        log.info("차단 목록 조회 시도: userId={}", userId);
        return blockMapper.selectBlock(userId);
    }
}

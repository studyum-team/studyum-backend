package com.studyumbackend.block.model.mapper;

import com.studyumbackend.block.model.dto.Block;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlockMapper {

    /**
     * 차단 추가
     * @param block 차단 정보 (blockerUserId, blockedUserId)
     * @return 영향받은 행의 수
     */
    int insertBlock(Block block);

    /**
     * 차단 삭제
     * @param blockerUserId 차단한 사람 ID
     * @param blockedUserId 차단당한 사람 ID
     * @return 영향받은 행의 수
     */
    int deleteBlock(Long blockerUserId, Long blockedUserId);

    /**
     * 차단 목록 조회 (단순 block 테이블 정보)
     * @param userId 차단한 사람 ID
     * @return 차단 객체 리스트
     */
    List<Long> selectBlock(Long userId);

    /**
     * 차단 목록 통합 조회 (상대방 유저 정보 포함)
     * @param userId 차단한 사람 ID
     * @return 상대방 정보가 포함된 DTO 리스트
     */
    List<Block> selectBlockUserInfo(Long userId);

    /**
     * 차단 여부 조회 (내가 상대를 차단했는가)
     * @param blockerUserId 내 ID
     * @param blockedUserId 상대 ID
     * @return 차단 여부 (true/false)
     */
    boolean existsBlock(Long blockerUserId, Long blockedUserId);

    /**
     * 사용자 차단 여부 조회 (상대가 나를 차단했는가)
     * @param blockerUserId 상대 ID
     * @param blockedUserId 내 ID
     * @return 차단 여부 (true/false)
     */
    boolean isMeBlockedByOther(Long blockerUserId, Long blockedUserId);

}
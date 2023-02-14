package com.hyunec.cosmicbaseballinit.domain.baseball.service;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.GameEntity;
import com.hyunec.cosmicbaseballinit.domain.baseball.repository.PlateAppearances;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameService {

    private final PlateAppearances plateAppearances;

    public BattingResult batting() {
        plateAppearances.batting(Batting.generate());
        log.info("### plateAppearances.result()={}", plateAppearances.result());
        return plateAppearances.result();
    }

    public GameEntity initGame() {
        if (plateAppearances.isFinished()) {
            plateAppearances.clear();
            return GameEntity.builder()
                .message("새로운 게임이 생성되었습니다.")
                .build();
        }
        return GameEntity.builder()
            .message("아직 게임이 끝나지 않았습니다.")
            .build();
    }
}

package com.hyunec.cosmicbaseballinit.roundGame.persistence.dao;

import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.RoundRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.OutAndScoreDto;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
@Repository
public class RoundDao implements RoundRepository {
    private Integer outCount = 0;
    private Integer scoreCount = 0;

    @Override
    public OutAndScoreDto getRoundScore() {
        return OutAndScoreDto.builder()
                .outCount(outCount)
                .scoreCount(scoreCount)
                .build();
    }

    @Override
    public void updateRoundScore(OutAndScoreDto outAndScoreDto) {
        this.outCount = outAndScoreDto.getOutCount();
        this.scoreCount = outAndScoreDto.getScoreCount();
    }

    @Override
    public void deleteRoundScore(OutAndScoreDto outAndScoreDto) {
        this.outCount = 0;
        this.scoreCount = 0;
    }
}

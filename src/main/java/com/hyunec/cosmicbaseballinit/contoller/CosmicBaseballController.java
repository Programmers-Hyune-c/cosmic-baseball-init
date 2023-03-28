package com.hyunec.cosmicbaseballinit.contoller;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;

import com.hyunec.cosmicbaseballinit.domain.BatterStatus;
import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;
import com.hyunec.cosmicbaseballinit.dto.BattingPatchDto;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.dto.ValidationDto;
import com.hyunec.cosmicbaseballinit.exception.BusinessException;
import com.hyunec.cosmicbaseballinit.exception.ExceptionType;
import com.hyunec.cosmicbaseballinit.service.GameService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
public class CosmicBaseballController {

    private final GameService gameService;

    @PostMapping("/games")
    public ResponseDto newGame(@Nullable @RequestParam("status") BatterStatus status) {
        validateOnGoing(status);
        return new ResponseDto(gameService.newGame());
    }

    @PostMapping("/games/fever")
    public ResponseDto feverInning(@Nullable @RequestParam("status") BatterStatus status) {
        validateOnGoing(status);

        ScoreBoard scoreBoard = gameService.newGame();
        scoreBoard.startFeverInning();

        return new ResponseDto(scoreBoard);
    }

    @PatchMapping("/batting/fever/{id}")
    public ResponseDto battingInFeverInning(
        @Min(1) @PathVariable Long id,
        @Valid @RequestBody ValidationDto dto){
        ScoreBoard feverScoreBoard = gameService.feverBatting(id);
        return new ResponseDto(feverScoreBoard);
    }

    @PatchMapping("/batting/{id}")
    public ResponseDto batting(
        @Min(1) @PathVariable Long id,
        @Valid @RequestBody BattingPatchDto patchDto
    ) {
        ScoreBoard updatedScoreBoard = gameService.batting(
                                                            id,
                                                            patchDto.getPercentage(),
                                                            patchDto.getTargetResult());
        return new ResponseDto(updatedScoreBoard);
    }

    private void validateOnGoing(BatterStatus status) {
        if (status == ON_GOING) {
            throw new BusinessException(ExceptionType.NO_NEW_BATTING_ON_GOING);
        }
    }
}

package com.hyunec.cosmicbaseballinit.contoller;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;

import com.hyunec.cosmicbaseballinit.domain.BatterStatus;
import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;
import com.hyunec.cosmicbaseballinit.dto.GamePatchDto;
import com.hyunec.cosmicbaseballinit.dto.NewGameDto;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.exception.BusinessException;
import com.hyunec.cosmicbaseballinit.exception.ExceptionType;
import com.hyunec.cosmicbaseballinit.service.GameService;
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

    private final GameServiceSelector gameServiceSelector;

    @PostMapping("/games")
    public ResponseDto newGame(
        @Nullable @RequestParam("status") BatterStatus status,
        @RequestBody NewGameDto newGameDto
    ) {
        validateOnGoing(status);
        GameService gameService = gameServiceSelector.get(newGameDto.getInningType());
        ScoreBoard scoreBoard = gameService.newGame();

        return new ResponseDto(scoreBoard);
    }

    @PatchMapping("/games/{id}")
    public ResponseDto battingInFeverInning(
        @Min(1) @PathVariable Long id,
        @RequestBody GamePatchDto patchDto
    ) {
        GameService gameService = gameServiceSelector.get(patchDto.getInningType());
        ScoreBoard scoreBoard = gameService.batting(
            id,
            patchDto.getPercentage(),
            patchDto.getTargetResult()
        );
        return new ResponseDto(scoreBoard);
    }

    private void validateOnGoing(BatterStatus status) {
        if (status == ON_GOING) {
            throw new BusinessException(ExceptionType.NO_NEW_BATTING_ON_GOING);
        }
    }
}

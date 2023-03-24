package com.hyunec.cosmicbaseballinit.contoller;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;

import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import com.hyunec.cosmicbaseballinit.dto.BattingPatchDto;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.exception.BusinessException;
import com.hyunec.cosmicbaseballinit.exception.ExceptionType;
import com.hyunec.cosmicbaseballinit.service.BattingService;
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

    private final BattingService battingService;

    @PostMapping("/batting/new")
    public ResponseDto newBatting(@Nullable @RequestParam("status") String status) {
        validateOnGoing(status);
        TotalBattingResult newBatting = battingService.newBatting();
        return new ResponseDto(newBatting);
    }

    @PatchMapping("/batting/{id}")
    public ResponseDto batting(@Min(1) @PathVariable Long id, @Valid @RequestBody BattingPatchDto patchDto) {

        TotalBattingResult totalBattingResult =
            battingService.batting(id, patchDto.getPercentage(), patchDto.getTargetResult());
        return new ResponseDto(totalBattingResult);
    }

    private void validateOnGoing(String status) {
        if (isOnGoing(status)) {
            throw new BusinessException(ExceptionType.NEW_BATTING);
        }
    }

    private boolean isOnGoing(String status) {
        return status != null && status.equals(ON_GOING.name());
    }
}

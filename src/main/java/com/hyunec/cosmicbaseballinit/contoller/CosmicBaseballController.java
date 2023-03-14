package com.hyunec.cosmicbaseballinit.contoller;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;

import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.exception.NewBattingException;
import com.hyunec.cosmicbaseballinit.service.BattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {

    private final BattingService battingService;

    @PostMapping("/batting/start")
    public ResponseDto startBatting(@Nullable @RequestParam("status") String status) {
        validateOnGoing(status);

        TotalBattingResult startBatting = battingService.startBatting();
        return new ResponseDto(startBatting);
    }


    @PatchMapping("/batting/{id}")
    public ResponseDto batting(@PathVariable Long id) {
        TotalBattingResult totalBattingResult = battingService.batting(id);
        return new ResponseDto(totalBattingResult);
    }

    private void validateOnGoing(String status) {
        if (isOnGoing(status)) {
            throw new NewBattingException("새로운 타석 안됨");
        }
    }

        private  boolean isOnGoing(String status) {
            return status != null && status.equals(ON_GOING.name());
        }
}

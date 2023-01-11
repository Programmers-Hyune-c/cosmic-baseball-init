package com.hyunec.cosmicbaseballinit.roundGame.controller;

import com.hyunec.cosmicbaseballinit.config.ReadMessageYml;
import com.hyunec.cosmicbaseballinit.controller.HitterGameInterface;
import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.RoundGameResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingRequestVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/roundGame")
public class RoundGameController {
    private final RoundGameInterface roundGameInterface;
    private final HitterGameInterface hitterGameInterface;
    private final ReadMessageYml readMessageYml;

    @GetMapping("/health")
    public String health() {
        return "good";
    }

    @GetMapping("/setting")
    public String gameSetting() {
        return hitterGameInterface.gameSetting();
    }

    @PostMapping("/setting")
    public String gameSetting(@RequestBody PitchProbabilitySettingVo pitchProbabilitySettingVo) {
        return hitterGameInterface.gameSetting(pitchProbabilitySettingVo);
    }

    @GetMapping("/hitting")
    public String hitting() throws Exception {
        return roundGameInterface.hitting();
    }

    @PostMapping("/hitting")
    public String hitting(@RequestBody HittingRequestVo hittingRequestVo) throws Exception {
        return roundGameInterface.hitting(hittingRequestVo);
    }

    @GetMapping("/init")
    public String initRoundGame() {
        roundGameInterface.initRoundGame();
        return readMessageYml.getInitFinish();
    }

    @GetMapping("/score")
    public RoundGameResult getScore() {
        return roundGameInterface.getRoundGameResult();
    }


}

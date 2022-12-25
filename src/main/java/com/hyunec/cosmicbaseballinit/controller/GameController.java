package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.config.ReadMessageYml;
import com.hyunec.cosmicbaseballinit.service.lv1.Lv1HitterGameService;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final Lv1HitterGameService gameService;
    private final ReadMessageYml readMessageYml;

    @GetMapping("/game/setting")
    public String gameSetting(){
        gameService.setHitGameProbability();
        return readMessageYml.getSettingFinished();
    }

    @GetMapping("/game/hitting")
    public String hitting() throws Exception{
        String hittingResult = gameService.hitting();
        return hittingResult;
    }

    @GetMapping("/game/initScore")
    public void initScore() {
        gameService.initGameScore();
    }

    @GetMapping("/game/hitterScore")
    public Map<PitchResult, Integer> hitterScore(){
        return gameService.getScores();
    }

    //FIXME 테스트용, 에러메세지를 반환하지 않는 에러
    @GetMapping("test")
    public void nonErrorMessageTest() throws Exception{
        throw new Exception();
    }
}

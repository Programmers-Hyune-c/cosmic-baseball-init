package com.hyunec.cosmicbaseballinit.contoller;

import static com.hyunec.cosmicbaseballinit.contoller.InningType.FEVER;

import com.hyunec.cosmicbaseballinit.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class GameServiceSelector {

    private final GameService feverGameService;
    private final GameService normalGameService;

    public GameService get(InningType inningType) {
        return inningType == FEVER ? feverGameService : normalGameService;
    }
}

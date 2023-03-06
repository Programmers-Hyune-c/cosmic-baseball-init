package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.service.BallCountService.COUNT_STORE;

import com.hyunec.cosmicbaseballinit.service.BallCountService;

public enum BattingResult implements BallCountSetter {
    STRIKE {
        @Override
        public void call() {
            COUNT_STORE.compute("STRIKE", (k, v) -> v + 1);
        }
    },
    BALL{
        @Override
        public void call() {
            COUNT_STORE.compute("BALL", (k, v) -> v + 1);
        }
    },
    DOUBLE_STRIKE{
        @Override
        public void call() {
            COUNT_STORE.compute("STRIKE", (k, v) -> v + 2);
        }
    },
    DOUBLE_BALL{
        @Override
        public void call() {
            COUNT_STORE.compute("BALL", (k, v) -> v + 2);
        }
    },
    HIT{
        @Override
        public void call() {
            BallCountService.resetBallCount();
        }
    };

}

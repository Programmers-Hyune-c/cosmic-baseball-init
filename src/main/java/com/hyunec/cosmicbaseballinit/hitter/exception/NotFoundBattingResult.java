package com.hyunec.cosmicbaseballinit.hitter.exception;

public class NotFoundBattingResult extends RuntimeException {

    public NotFoundBattingResult() {
        super("배팅 결과를 찾을 수 없습니다.");
    }
}

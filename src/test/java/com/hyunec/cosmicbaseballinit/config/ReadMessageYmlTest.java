package com.hyunec.cosmicbaseballinit.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReadMessageYmlTest {
    @Autowired
    ReadMessageYml readMessageYml;

    @Test
    void messageTest() {
        // when
        String settingFirstMessage = readMessageYml.getSettingFirst();
        String scoreInitMessage = readMessageYml.getScoreInit();
        String settingFinished = readMessageYml.getSettingFinished();

        // then
        assertThat(settingFirstMessage).isEqualTo("게임 확률 세팅을 먼저 해주세요");
        assertThat(scoreInitMessage).isEqualTo("점수가 초기화 되었습니다");
        assertThat(settingFinished).isEqualTo("게임 세팅이 완료 되었습니다");

    }
}

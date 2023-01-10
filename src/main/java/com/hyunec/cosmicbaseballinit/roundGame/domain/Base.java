package com.hyunec.cosmicbaseballinit.roundGame.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

@Builder
public class Base {
    private final Queue<Integer> bases;
    private static final Integer TOTAL_BASE_COUNT = 3; // 총 베이스 수

    public Base(Queue<Integer> bases) {
        this.bases = bases;
    }

    public Queue<Integer> get() {
        return bases;
    }

    public static Integer getTotalBaseCount() {
        return TOTAL_BASE_COUNT;
    }
    public Integer size() {
        return bases.size();
    }

    /**
     * 진루: 큐에 1 추가
     * @return Base : 1 추가된 Base 객체 반환
     */
    public Base advancingBase() {
        bases.offer(1);
        return new Base(bases);
    }

    /**
     * 홈베이스에 들어온 타자의 수 반환
     * @return Integer : 홈베이스에 들어온 타자의 수
     */
    public Integer getHitterCountInHomeBase() {
        if (bases.size() >= TOTAL_BASE_COUNT) {
            return (bases.size() - TOTAL_BASE_COUNT);
        }
        return 0;
    }
    
    /**
     * 입력값 만큼 bases에서 제거(홈베이스에 들어온 인원 제거)
     * @param count
     * @return Base: 제거한 후 Base 객체
     */
    public Base removeHitterInHomeBase(Integer count) {
        for(int i = 0; i < count; i++) {
            bases.poll();
        }
        return new Base(bases);
    }
}

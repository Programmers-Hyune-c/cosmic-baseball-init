package com.hyunec.cosmicbaseballinit.domain.baseball.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewGameResponse {
  private String message;
  private LocalDateTime dateTime;
}

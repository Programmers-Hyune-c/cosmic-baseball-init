package com.hyunec.cosmicbaseballinit.domain.baseball.model.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class NewGameResponse {
  private String message;
  private LocalDateTime dateTime;
}

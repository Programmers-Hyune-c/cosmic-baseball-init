package com.hyunec.cosmicbaseballinit.domain.baseball.model.dto;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NewGameResponse {
  private String message;
  private LocalDateTime dateTime;
  private HttpStatus httpStatus;
}

package com.hyunec.cosmicbaseballinit.domain.baseball.model.exception;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.dto.NewGameResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {IllegalStateException.class})
  public NewGameResponse handleNewGameException(IllegalStateException e) {
    return new NewGameResponse(e.getMessage(), LocalDateTime.now());
  }

}

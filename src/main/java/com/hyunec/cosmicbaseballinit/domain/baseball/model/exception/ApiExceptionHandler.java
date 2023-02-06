package com.hyunec.cosmicbaseballinit.domain.baseball.model.exception;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.dto.NewGameResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {IllegalStateException.class})
  public ResponseEntity<Object> handleNewGameException(IllegalStateException e) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    NewGameResponse result = new NewGameResponse();
    result.setMessage(e.getMessage());
    result.setDateTime(LocalDateTime.now());

    return new ResponseEntity<>(result, status);
  }

}

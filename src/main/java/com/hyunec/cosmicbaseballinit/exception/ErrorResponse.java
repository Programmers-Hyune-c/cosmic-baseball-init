package com.hyunec.cosmicbaseballinit.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
@JsonInclude(Include.NON_EMPTY)
public class ErrorResponse {

    private final String message;
    private final List<CustomFieldError> customFieldError;

    @Builder
    private ErrorResponse(String message, List<CustomFieldError> customFieldError) {
        this.message = message;
        this.customFieldError = customFieldError;
    }

    public static ErrorResponse of(String message) {
        return ErrorResponse.builder().message(message).build();
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return ErrorResponse.builder().customFieldError(CustomFieldError.of(bindingResult)).build();
    }

    @Getter
    private static class CustomFieldError {

        private final String field;
        private final Object rejectedValue;
        private final String message;

        private CustomFieldError(String field, Object rejectedValue, String message) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.message = message;
        }

        public static List<CustomFieldError> of(BindingResult bindingResult) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream().map(fieldError ->
                new CustomFieldError(
                    fieldError.getField(),
                    fieldError.getRejectedValue(),
                    fieldError.getDefaultMessage()
                )
            ).collect(Collectors.toList());
        }
    }
}

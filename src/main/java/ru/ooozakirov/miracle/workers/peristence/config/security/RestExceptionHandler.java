package ru.ooozakirov.miracle.workers.peristence.config.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ooozakirov.miracle.workers.peristence.dto.auth.ErrorDto;
import ru.ooozakirov.miracle.workers.peristence.exception.AppException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { AppException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorDto().setMessage(ex.getMessage()));
    }
}

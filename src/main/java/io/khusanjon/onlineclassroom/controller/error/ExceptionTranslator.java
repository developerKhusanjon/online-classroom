package io.khusanjon.onlineclassroom.controller.error;

import com.fasterxml.jackson.databind.JsonMappingException;
import io.khusanjon.onlineclassroom.security.UserNotActivatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(400).body(errors);
    }

    @ExceptionHandler
    private ResponseEntity<BadRequestAlertException> handleBadRequestAlertException(BadRequestAlertException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(404).body(new BadRequestAlertException(ex.getMessage(), "user", "phone", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler
    private ResponseEntity<?> handleUserNotActivatedException(UserNotActivatedException ex) {
        return ResponseEntity.status(403).body(new BadRequestAlertException(ex.getMessage(), "user", "phone", HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleJacksonError(final JsonMappingException ex, final WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

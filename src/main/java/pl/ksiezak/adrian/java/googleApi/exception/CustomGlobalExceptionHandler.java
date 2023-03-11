package pl.ksiezak.adrian.java.googleApi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> messages = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        String path = request.getDescription(false).substring(4);
        String method = ((ServletWebRequest) request).getHttpMethod().toString();

        CustomExceptionDto dto = CustomExceptionDto.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .messages(messages)
                .path(path)
                .method(method)
                .build();

        return handleExceptionInternal(ex, dto, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleError(ConstraintViolationException e, WebRequest request) {
        List<String> messages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        String path = request.getDescription(false).substring(4);
        String method = ((ServletWebRequest) request).getHttpMethod().toString();

        CustomExceptionDto dto = CustomExceptionDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .messages(messages)
                .path(path)
                .method(method)
                .build();

        return handleExceptionInternal(e, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
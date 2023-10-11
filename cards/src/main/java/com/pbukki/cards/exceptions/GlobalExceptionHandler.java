package com.pbukki.cards.exceptions;

import com.pbukki.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest webRequest){

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error->{
            String field = ((FieldError)error).getField();
            String msg = error.getDefaultMessage();
            errors.put(field,msg);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> globalExceptionHandler(Exception ex, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundHandler(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);

    }

}

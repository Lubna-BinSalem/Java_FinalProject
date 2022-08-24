package com.example.bookatable.Advice;

import com.example.bookatable.Exceptions.ApiException;
import com.example.bookatable.Model.Api;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Api> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.status(400).body(new Api(methodArgumentNotValidException.getFieldError().getDefaultMessage(), 400));
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Api> dataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException){
        return ResponseEntity.status(400).body(new Api(dataIntegrityViolationException.getLocalizedMessage(),400));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Api> constraintViolationException(ConstraintViolationException constraintViolationException){
        return ResponseEntity.status(400).body(new Api("a constraint has been violated",400));
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api> apiException(ApiException apiException) {
        return ResponseEntity.status(400).body(new Api(apiException.getMessage(), 400));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api> exception(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.status(500).body(new Api("Server error!", 500));
    }
}

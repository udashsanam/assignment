package com.project.coffeshop.exception;

import com.project.coffeshop.enums.ResponseStatusEnum;
import com.project.coffeshop.pojo.response.GlobalApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHelper extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleRuntimeExceptin(RuntimeException exception){
        return ResponseEntity.ok(GlobalApiResponse.builder().status(ResponseStatusEnum.FAIL).message(exception.getMessage()).data(null).build());
    }

}

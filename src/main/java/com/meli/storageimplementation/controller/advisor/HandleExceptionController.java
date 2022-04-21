package com.meli.storageimplementation.controller.advisor;

import com.meli.storageimplementation.errors.TestCaseDoesNotExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleExceptionController {

    @ExceptionHandler(TestCaseDoesNotExistsException.class)
    public ResponseEntity<Void> testCaseDoesNotExistsException(TestCaseDoesNotExistsException exception) {
        return ResponseEntity.notFound().build();
    }

}

package com.meli.storageimplementation.controller.advisor;

import com.meli.storageimplementation.errors.ErrorDTO;
import com.meli.storageimplementation.errors.TestCaseDoesNotExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;

@RestControllerAdvice
public class HandleExceptionController {

    @ExceptionHandler(TestCaseDoesNotExistsException.class)
    public ResponseEntity<Void> testCaseDoesNotExistsException(TestCaseDoesNotExistsException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorDTO> parseException(ParseException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setError("Não foi possível converter a data.");
        error.setMessage("O formato de data deve ser dd-mm-yyyy");
        return ResponseEntity.badRequest().body(error);
    }

}

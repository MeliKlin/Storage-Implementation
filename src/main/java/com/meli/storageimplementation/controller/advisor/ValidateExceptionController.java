package com.meli.storageimplementation.controller.advisor;

import com.meli.storageimplementation.errors.IdIsRequiredException;
import com.meli.storageimplementation.errors.JewelNotFoundException;
import com.meli.storageimplementation.errors.ValidationErrorDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidateExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationErrorDTO> handleValidation(MethodArgumentNotValidException exception) {
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ValidationErrorDTO response = new ValidationErrorDTO("Something went wrong, check all fields.", errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(JewelNotFoundException.class)
    protected ResponseEntity<Void> handleJewelNotFoundException(JewelNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IdIsRequiredException.class)
    protected ResponseEntity<ValidationErrorDTO> handleIdIsRequiredException(IdIsRequiredException exception) {
        List<String> errors = List.of(new String[]{exception.getMessage()});
        ValidationErrorDTO response = new ValidationErrorDTO("Something went wrong with cod_identificacao", errors);
        return ResponseEntity.badRequest().body(response);
    }

}

package com.meli.storageimplementation.controller.advisor;

import com.meli.storageimplementation.error.ValidationErrorDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandleExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationErrorDTO> handleValidationError(MethodArgumentNotValidException exception) {
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ValidationErrorDTO errorResponse = new ValidationErrorDTO();
        errorResponse.setMessage("Verifique se todos os campos foram preenchidos corretamente.");
        errorResponse.setErrors(errors);

        return ResponseEntity.badRequest().body(errorResponse);
    }

}

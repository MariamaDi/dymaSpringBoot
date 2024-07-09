package com.dyma.tennis.web;

import com.dyma.tennis.Error;
import com.dyma.tennis.service.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class PlayerControllerErrorHandler {
    //quand une erreur de type NoSuchElementException va etre intercepté
    //c'est la methode handleNoElement qui va etre appelé
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handlePlayerNotFoundException(PlayerNotFoundException ex){
       return new Error(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ex){
        var errors= new HashMap<String,String>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName=((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
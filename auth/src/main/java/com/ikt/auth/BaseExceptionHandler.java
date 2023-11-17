package com.ikt.auth;

import com.ikt.auth.exception.NotFoundException;
import com.ikt.auth.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException (NotFoundException notFoundException){
        return new ResponseEntity(notFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity handleAlreadyExist (UserAlreadyExistException userAlreadyExistException){
        return new ResponseEntity(userAlreadyExistException.getLocalizedMessage(), HttpStatus.CONFLICT);
    }
}

package com.ikt.auth.exception;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(String message){
        super(message);
    }
}
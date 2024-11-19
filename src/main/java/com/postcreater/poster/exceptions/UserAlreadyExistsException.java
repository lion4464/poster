package com.postcreater.poster.exceptions;

public class UserAlreadyExistsException extends ApiException{

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

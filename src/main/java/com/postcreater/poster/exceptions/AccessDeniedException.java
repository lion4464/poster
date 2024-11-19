package com.postcreater.poster.exceptions;

public class AccessDeniedException extends ApiException {
    public AccessDeniedException(String message) {
        super(message);
    }
}

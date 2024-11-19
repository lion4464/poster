package com.postcreater.poster.exceptions;

import jakarta.validation.constraints.NotBlank;

public class BadCredentialsException extends ApiException{
    public BadCredentialsException(String message) {
        super(message);
    }
}

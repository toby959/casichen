package com.urbannext.api.exception;

public class TokenVerificationException extends RuntimeException{
    public TokenVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}

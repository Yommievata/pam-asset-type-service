package com.ikea.imc.pam.asset.type.service.exception;

public abstract class RequestException extends RuntimeException {
    RequestException(String message) {
        super(message);
    }
}

package com.santex.demo.exception;

public class CustomRestTemplateException extends RuntimeException {

    private String error;

    public CustomRestTemplateException(String error) {
        super(error);
        this.error = error;
    }

}

package com.unittest.retrofitsample.melisma.model;

public class Hello {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Hello(String message) {
        this.message = message;
    }
}
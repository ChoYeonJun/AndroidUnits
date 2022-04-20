package com.unittest.retrofitsample.model;

public class LoginReqUserDto {
    private String email;
    private String password;


    public LoginReqUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
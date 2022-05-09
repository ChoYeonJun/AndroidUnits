package com.unittest.retrofitsample.melisma.model.dto;

public class LoginReqUserDto {
    private String username;
    private String password;


    public LoginReqUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setUsername(String email) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
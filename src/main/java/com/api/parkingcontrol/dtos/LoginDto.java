package com.api.parkingcontrol.dtos;

import javax.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

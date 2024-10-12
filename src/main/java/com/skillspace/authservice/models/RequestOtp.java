package com.skillspace.authservice.models;

public class RequestOtp {
    private String email;
    private boolean forgotPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(boolean forgotPassword) {
        this.forgotPassword = forgotPassword;
    }
}


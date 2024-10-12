package com.skillspace.authservice.models;

public class Otp {
    int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Otp{" +
                "code=" + code +
                '}';
    }
}

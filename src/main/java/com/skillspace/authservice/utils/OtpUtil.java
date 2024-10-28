package com.skillspace.authservice.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {
    Random rand = new Random();
    public int generateFourDigitOtp(){

        return rand.nextInt(1000,9999);
    }
    public int generateSixDigitOtp(){

        return rand.nextInt(100000,999999);
    }
}

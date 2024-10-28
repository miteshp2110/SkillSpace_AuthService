package com.skillspace.authservice.models;

import org.springframework.stereotype.Component;

@Component
public class RequestBody {

    public String otpRequestBody(String email,  String company , int code){


        return String.format(
                """
                {
                    "company":"%s",
                    "otp" : "%d",
                    "recipient":"%s"
                }
                """,company,code,email
        );
    }
}

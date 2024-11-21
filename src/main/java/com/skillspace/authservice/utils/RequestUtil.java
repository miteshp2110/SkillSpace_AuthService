package com.skillspace.authservice.utils;

import com.skillspace.authservice.models.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Component
public class RequestUtil {

    @Autowired
    RequestBody jsonRequestTemplate;

    @Value("${email_service_host}")
    private String host;

    @Value("${email_service_port}")
    private String port;

    @Value("${email_service_apiKey}")
    private String apiKey;

    @Value("${email_service_url}")
    private String url;

    public String sentPostRequest(String endpoint , String body) {

//        String uri = "http://" + host + ":" + port + "/send" + endpoint;
        String uri = url + "/send" + endpoint;
//        System.out.println(uri);
//        System.out.println(apiKey);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("Authorization",apiKey);


        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, httpEntity, String.class);

        return response.getBody();

    }
}

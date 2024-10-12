package com.skillspace.authservice.controllers;


import com.skillspace.authservice.models.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PofileController {

    @PostMapping("/completeProfile")
    public String completeProfile(@RequestBody Profile profile) {
        System.out.println(profile);
        return "You are filling profile details";
    }

    @GetMapping("/departments")
    public String departments(){
        return "You are getting departments";
    }

}

package com.skillspace.authservice.controllers;


import com.skillspace.authservice.models.Departments;
import com.skillspace.authservice.models.Profile;
import com.skillspace.authservice.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/completeProfile")
    public String completeProfile(@RequestBody Profile profile) {
        System.out.println(profile);
        return "You are filling profile details";
    }

    @GetMapping("/departments")
    public List<Departments> departments()
    {
        return departmentService.getAllDepartments();
    }

}

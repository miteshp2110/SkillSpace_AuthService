package com.skillspace.authservice.controllers;


import com.skillspace.authservice.models.Departments;
import com.skillspace.authservice.models.Profile;
import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.services.DepartmentService;
import com.skillspace.authservice.services.S3Service;
import com.skillspace.authservice.services.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    S3Service s3Service;

    @Autowired
    StudentProfileService studentProfileService;

//    @PostMapping("/completeProfile")
//    public String completeProfile(@RequestBody Profile profile) {
//        System.out.println(profile);
//        return "You are filling profile details";
//    }

    @PostMapping("/completeProfile")
    public ResponseEntity<Response> completeProfile(@RequestHeader("Authorization") String token , @RequestParam("file") MultipartFile
                                  file, @RequestParam("name") String name , @RequestParam("branch")
                                  String branch, @RequestParam("bio")String bio) throws Exception {

        return studentProfileService.setStudentProfile(token,file,name,bio,branch);
    }

    @GetMapping("/departments")
    public List<Departments> departments()
    {
        return departmentService.getAllDepartments();
    }

}

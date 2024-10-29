package com.skillspace.authservice.services;

import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.models.TeacherProfile;
import com.skillspace.authservice.repository.TeacherRepository;
import com.skillspace.authservice.repository.UserRepository;
import com.skillspace.authservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Response> setTeacherProfile(String token, String name , String department){
        Response response = new Response();
        try{
            token = token.replace("Bearer ", "");
            String email = jwtUtil.extractUsername(token);
            TeacherProfile teacherProfile = new TeacherProfile();
            teacherProfile.setEmail(email);
            teacherProfile.setName(name);
            teacherProfile.setDepartment(department);
            teacherRepository.save(teacherProfile);
            userRepository.updateProfileStatus(true,email);
            response.setMessage("Teacher profile updated successfully");
            response.setError(false);
            return ResponseEntity.status(201).body(response);
        }
        catch(Exception e){
            response.setError(true);
            response.setMessage("Unable to update teacher profile");
            return ResponseEntity.status(500).body(response);

        }
    }
}

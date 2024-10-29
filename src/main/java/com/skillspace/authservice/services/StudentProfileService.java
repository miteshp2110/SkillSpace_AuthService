package com.skillspace.authservice.services;


import com.skillspace.authservice.models.Profile;
import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.repository.StudentProfileRepository;
import com.skillspace.authservice.repository.UserRepository;
import com.skillspace.authservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentProfileService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    S3Service s3Service;

    @Autowired
    StudentProfileRepository studentProfileRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Response> setStudentProfile(String token, MultipartFile file,String name,String bio,String Branch)

    {
        try{
            token = token.replace("Bearer ", "");
            Response response = new Response();
            String email = jwtUtil.extractUsername(token);
            String imgUrl = s3Service.uploadFile(file,email);
            Profile profile = new Profile();
            profile.setName(name);
            profile.setBio(bio);
            profile.setBranch(Branch);
            profile.setEmail(email);
            profile.setProfile_image_url(imgUrl);
            studentProfileRepository.save(profile);
            userRepository.updateProfileStatus(true,email);

            response.setMessage("Successfully set profile");
            response.setError(false);

            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            Response response = new Response();
            response.setError(true);
            response.setMessage("Error setting profile");
            return ResponseEntity.status(500).body(response);
        }

    }
}

package com.skillspace.authservice.repository;

import com.skillspace.authservice.models.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface OtpRepository extends JpaRepository<Otp,String> {

    @Query(value = "select code from otp where email =:email",nativeQuery = true)
    public Object getOtpCode(@Param("email") String email);


}

package com.skillspace.authservice.repository;

import com.skillspace.authservice.models.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherProfile,String> {


}

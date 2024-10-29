package com.skillspace.authservice.repository;

import com.skillspace.authservice.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<Profile,String> {

}

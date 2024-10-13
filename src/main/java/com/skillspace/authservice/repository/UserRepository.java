package com.skillspace.authservice.repository;

import com.skillspace.authservice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Long> {

//    @Query(value = "insert into users (id,email,)")
//    public String insertUser(Users users);


}

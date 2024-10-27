package com.skillspace.authservice.repository;

import com.skillspace.authservice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {

//    @Query(value = "insert into users (id,email,)")
//    public String insertUser(Users users);


    @Query(value = "select count(*) from users where email = :email",nativeQuery = true)
    public int existingUser(@Param("email") String email);

    @Query(value = "select role from users where email =:email",nativeQuery = true)
    public String findRoleByEmail(@Param("email") String email);

    @Query(value = "select password from users where email =:email",nativeQuery = true)
    public String findPasswordByEmail(@Param("email") String email);


}

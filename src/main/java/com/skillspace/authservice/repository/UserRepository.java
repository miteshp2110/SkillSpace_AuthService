package com.skillspace.authservice.repository;

import com.skillspace.authservice.models.Users;
import jakarta.persistence.Table;
import jdk.jfr.MetadataDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<Users, Long> {

//    @Query(value = "insert into users (id,email,)")
//    public String insertUser(Users users);


    @Query(value = "select count(*) from users where email = :email",nativeQuery = true)
    public int existingUser(@Param("email") String email);

    @Query(value = "select role from users where email =:email",nativeQuery = true)
    public String findRoleByEmail(@Param("email") String email);

    @Query(value = "select password from users where email =:email",nativeQuery = true)
    public String findPasswordByEmail(@Param("email") String email);


    @Modifying
    @Transactional
    @Query(value = "update users set emailStatus =:emailStatus where email =:email",nativeQuery = true)
    public int updateEmailStatus(@Param("email") String email, @Param("emailStatus") boolean emailStatus);

    @Modifying
    @Transactional
    @Query(value = "update users set password =:newPassword where email =:email",nativeQuery = true)
    public int updatePassword(@Param("newPassword") String newPassword, @Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "update users set profileStatus =:profileStatus where email =:email",nativeQuery = true)
    public int updateProfileStatus(@Param("profileStatus") boolean profileStatus, @Param("email") String email);


}

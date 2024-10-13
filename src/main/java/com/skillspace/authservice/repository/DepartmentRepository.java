package com.skillspace.authservice.repository;

import com.skillspace.authservice.models.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments,String> {
}

package com.skillspace.authservice.services;


import com.skillspace.authservice.models.Departments;
import com.skillspace.authservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Departments> getAllDepartments() {
        return departmentRepository.findAll();
    }
}

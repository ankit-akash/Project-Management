package com.EmployeeService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeService.entity.Employee;

import java.util.List;

@Repository			// Marks this interface as a Spring Data repository
//Provides CRUD operations
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    // Find employees by project ID
    List<Employee> findByProjectId(long courseId);

}

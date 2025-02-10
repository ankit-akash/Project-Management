package com.EmployeeService.DTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.EmployeeService.entity.Employee;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private long projectId;
    private String projectName;
    private String projectDesc;

    private List<Employee> employees=new ArrayList<>();
}

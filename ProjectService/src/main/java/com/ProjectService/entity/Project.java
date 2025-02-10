package com.ProjectService.entity;

import java.util.ArrayList;
import java.util.List;

import com.ProjectService.DTO.EmployeeDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity			// Marks this class as a JPA entity
public class Project {
    @Id
    @Min(value = 1, message = "Project ID must be greater than 0")
    private long projectId;

    @NotBlank(message = "Project name is mandatory")
    @Size(min = 2, max = 100, message = "Project name must be between 2 and 100 characters")
    private String projectName;

    @NotBlank(message = "Project description is mandatory")
    @Size(min = 10, max = 500, message = "Project description must be between 10 and 500 characters")
    private String projectDesc;
    
    // It tells the JPA provider to ignore this field when performing database operations.
    @Transient		// Temporary data storage
    private List<EmployeeDTO> employees = new ArrayList<>();
}

package com.EmployeeService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
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
public class Employee {
    @Id
    @Min(value = 1, message = "Employee ID must be greater than 0")
    private long employeeId;

    @NotBlank(message = "Employee name is mandatory")
    @Size(min = 2, max = 100, message = "Employee name must be between 2 and 100 characters")
    private String employeeName;
    
    @NotBlank(message = "Employee email is mandatory")
    @Email(message = "Email should be valid")
    private String employeeEmail;

    @Min(value = 1, message = "Project ID must be greater than 0")
    private long projectId;  // Project ID which the employee is associated
}

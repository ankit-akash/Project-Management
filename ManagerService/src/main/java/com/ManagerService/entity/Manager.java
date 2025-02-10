package com.ManagerService.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data		// Getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity				// Mark this class as JPA Entity
public class Manager {		// Stores the data into database
    @Id
    @Min(value = 1, message = "Manager ID must be greater than 0")
    private long managerId;

    @NotBlank(message = "Manager name is mandatory")
    @Size(min = 2, max = 100, message = "Manager name must be between 2 and 100 characters")
    private String managerName;

    @NotBlank(message = "Manager email is mandatory")
    @Email(message = "Email should be valid")
    private String managerEmail;

    @NotNull(message = "Project ID is mandatory")
    @Min(value = 1, message = "Project ID must be greater than 0")
    private long projectId;	// Project ID the manager is associated with
}
package com.ProjectService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//transfer employee data
public class EmployeeDTO {
    private long employeeId;
    private String employeeName;
    private String employeeEmail;
    private long projectId;
}

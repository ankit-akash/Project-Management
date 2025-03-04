package com.ProjectService.externalServices;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ProjectService.DTO.EmployeeDTO;

public interface ManagerServices {

    @GetMapping("/getByIdForManager/{projectId}")
    public List<EmployeeDTO> getAllEmployeesByProjectId(@PathVariable long projectId);
}

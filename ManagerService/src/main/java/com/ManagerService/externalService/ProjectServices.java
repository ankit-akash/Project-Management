package com.ManagerService.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ManagerService.DTO.ProjectDTO;

@FeignClient(name = "PROJECTSERVICE", url="http://localhost:8055/project")
public interface ProjectServices {

	// Get project by ID for manager
    @GetMapping("/getByIdForManager/{projectId}")
    public ProjectDTO getProjectByIdForManager(@PathVariable long projectId);

}

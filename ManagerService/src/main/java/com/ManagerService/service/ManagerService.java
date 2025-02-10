package com.ManagerService.service;

import java.util.List;

import com.ManagerService.DTO.ProjectDTO;
import com.ManagerService.entity.Manager;

public interface ManagerService {

    public List<Manager> getAllManager();

    public Manager getManagerById(long managerId);

    public String deleteManagerById(long managerId);

    public ProjectDTO getAssignProjectByManagerId(long managerId);
    
    public Manager assignProjectToManager(long managerId,long projectId);
    
}

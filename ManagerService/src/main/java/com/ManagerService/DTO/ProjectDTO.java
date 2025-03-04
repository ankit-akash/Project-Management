package com.ManagerService.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private long projectId;
    private String projectName;
    private String projectDesc;
    List<ManagerDTO> manager;
}

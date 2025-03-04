package com.ManagerService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ManagerDTO {
    private long managerId;
    private String managerName;
    private String managerEmail;
    private long projectId;

}

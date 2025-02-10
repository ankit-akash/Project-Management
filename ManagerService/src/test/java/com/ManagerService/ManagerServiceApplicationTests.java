package com.ManagerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ManagerService.DTO.ProjectDTO;
import com.ManagerService.entity.Manager;
import com.ManagerService.exceptions.DeleteManagerByIdNotFoundException;
import com.ManagerService.exceptions.ManagerNotFoundByIdException;
import com.ManagerService.exceptions.ManagerNotFoundException;
import com.ManagerService.externalService.ProjectServices;
import com.ManagerService.repo.ManagerRepo;
import com.ManagerService.service.ManagerServiceImpl;

public class ManagerServiceApplicationTests {

    @Mock
    private ManagerRepo managerRepo;

    @Mock
    private ProjectServices projectServices;

    @InjectMocks
    private ManagerServiceImpl managerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEmployee() {
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setManagerName("Test Manager");

        when(managerRepo.save(manager)).thenReturn(manager);

        Manager result = managerService.addManager(manager);
        assertEquals(manager, result);
    }

    @Test
    public void testGetAllManager() {
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setManagerName("Test Manager");

        List<Manager> managers = Arrays.asList(manager);

        when(managerRepo.findAll()).thenReturn(managers);

        List<Manager> result = managerService.getAllManager();
        assertEquals(managers, result);
    }

    @Test
    public void testGetAllManager_EmptyList() {
        when(managerRepo.findAll()).thenReturn(Arrays.asList());

        assertThrows(ManagerNotFoundException.class, () -> {
            managerService.getAllManager();
        });
    }

    @Test
    public void testGetManagerById() {
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setManagerName("Test Manager");

        when(managerRepo.findById(1L)).thenReturn(Optional.of(manager));

        Manager result = managerService.getManagerById(1L);
        assertEquals(manager, result);
    }

    @Test
    public void testGetManagerById_NotFound() {
        when(managerRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ManagerNotFoundByIdException.class, () -> {
            managerService.getManagerById(1L);
        });
    }

    @Test
    public void testDeleteManagerById() {
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setManagerName("Test Manager");

        when(managerRepo.findById(1L)).thenReturn(Optional.of(manager));
        doNothing().when(managerRepo).deleteById(1L);

        String result = managerService.deleteManagerById(1L);
        assertEquals("Manager Deleted Successfully", result);
    }

    @Test
    public void testDeleteManagerById_NotFound() {
        when(managerRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeleteManagerByIdNotFoundException.class, () -> {
            managerService.deleteManagerById(1L);
        });
    }

    @Test
    public void testGetAssignProjectByManagerId() {
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setManagerName("Test Manager");
        manager.setProjectId(1L);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(1L);

        when(managerRepo.findById(1L)).thenReturn(Optional.of(manager));
        when(projectServices.getProjectByIdForManager(1L)).thenReturn(projectDTO);

        ProjectDTO result = managerService.getAssignProjectByManagerId(1L);
        assertEquals(projectDTO, result);
    }

    @Test
    public void testGetAssignProjectByManagerId_ManagerNotFound() {
        when(managerRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ManagerNotFoundByIdException.class, () -> {
            managerService.getAssignProjectByManagerId(1L);
        });
    }

    @Test
    public void testAssignProjectToManager() {
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setManagerName("Test Manager");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(1L);

        when(managerRepo.findById(1L)).thenReturn(Optional.of(manager));
        when(projectServices.getProjectByIdForManager(1L)).thenReturn(projectDTO);
        when(managerRepo.save(manager)).thenReturn(manager);

        Manager result = managerService.assignProjectToManager(1L, 1L);
        assertEquals(manager, result);
    }

    @Test
    public void testAssignProjectToManager_ManagerNotFound() {
        when(managerRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ManagerNotFoundByIdException.class, () -> {
            managerService.assignProjectToManager(1L, 1L);
        });
    }
}
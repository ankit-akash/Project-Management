package com.ProjectService;

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

import com.ProjectService.DTO.EmployeeDTO;
import com.ProjectService.DTO.ProjectDTO;
import com.ProjectService.entity.Project;
import com.ProjectService.exception.NoProjectFoundByIdException;
import com.ProjectService.exception.NoProjectFoundException;
import com.ProjectService.externalServices.EmployeeServices;
import com.ProjectService.repo.ProjectRepo;
import com.ProjectService.service.ProjectServiceImpl;

public class ProjectServiceApplicationTests {

    @Mock
    private ProjectRepo projectRepo;

    @Mock
    private EmployeeServices employeeServices;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProject() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");

        when(projectRepo.save(project)).thenReturn(project);

        Project result = projectService.addProject(project);
        assertEquals(project, result);
    }

    @Test
    public void testGetAllProject() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");

        List<Project> projects = Arrays.asList(project);

        when(projectRepo.findAll()).thenReturn(projects);

        List<ProjectDTO> result = projectService.getAllProject();
        assertEquals(1, result.size());
        assertEquals("Test Project", result.get(0).getProjectName());
    }

    @Test
    public void testGetAllProject_EmptyList() {
        when(projectRepo.findAll()).thenReturn(Arrays.asList());

        assertThrows(NoProjectFoundException.class, () -> {
            projectService.getAllProject();
        });
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");

        List<EmployeeDTO> employees = Arrays.asList(new EmployeeDTO(1L, "Test Employee", "test@example.com", 0));

        when(projectRepo.findById(1L)).thenReturn(Optional.of(project));
        when(employeeServices.getAllEmployeesByProjectId(1L)).thenReturn(employees);

        Project result = projectService.getProjectById(1L);
        assertEquals(project, result);
        assertEquals(1, result.getEmployees().size());
        assertEquals("Test Employee", result.getEmployees().get(0).getEmployeeName());
    }

    @Test
    public void testGetProjectById_NotFound() {
        when(projectRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoProjectFoundByIdException.class, () -> {
            projectService.getProjectById(1L);
        });
    }

    @Test
    public void testDeleteProjectById() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");

        when(projectRepo.findById(1L)).thenReturn(Optional.of(project));
        doNothing().when(projectRepo).deleteById(1L);

        String result = projectService.deleteProjectById(1L);
        assertEquals("Deleted", result);
    }

    @Test
    public void testDeleteProjectById_NotFound() {
        when(projectRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoProjectFoundByIdException.class, () -> {
            projectService.deleteProjectById(1L);
        });
    }

    @Test
    public void testGetProjectByIdForManager() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");

        when(projectRepo.findById(1L)).thenReturn(Optional.of(project));

        Project result = projectService.getProjectByIdForManager(1L);
        assertEquals(project, result);
    }

    @Test
    public void testGetProjectByIdForManager_NotFound() {
        when(projectRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoProjectFoundByIdException.class, () -> {
            projectService.getProjectByIdForManager(1L);
        });
    }
}
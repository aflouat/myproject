package fr.tmsconsult.myproject.task.controller;

import fr.tmsconsult.myproject.task.dto.ProjectDto;
import fr.tmsconsult.myproject.task.mapper.ProjectMapper;
import fr.tmsconsult.myproject.task.model.Project;
 import fr.tmsconsult.myproject.task.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class ProjectControllerUT {

    @Mock
    private ProjectService projectService;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectController projectController;

    private Project project;
    private ProjectDto projectDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        project = Project.builder().id(1L).name("TMS").build();
    }

    @Test
    public void testGetAllProjects() {
        List<Project> projects = Arrays.asList(project);
        when(projectService.getAllProjects()).thenReturn(projects);

        List<Project> result = projectController.getAllProjects();

        assertEquals(1, result.size());
        assertEquals("TMS", result.get(0).getName());
    }

    @Test
    public void testGetProjectById() {
        when(projectService.getProjectById(anyLong())).thenReturn(project);

        ResponseEntity<Project> response = projectController.getProjectById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TMS", response.getBody().getName());
    }

    @Test
    public void testCreateProject() {
        when(projectService.saveProject(any(ProjectDto.class))).thenReturn(projectDto);

        ProjectDto result = projectController.createProject(projectDto);

        assertEquals("TMS", result.getName());
    }

    @Test
    public void testUpdateProject() {
        when(projectService.updateProject(anyLong(), any(Project.class))).thenReturn(project);

        ResponseEntity<Project> response = projectController.updateProject(1L, project);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TMS", response.getBody().getName());
    }

    @Test
    public void testDeleteProject() {
        doNothing().when(projectService).deleteProject(anyLong());

        ResponseEntity<Void> response = projectController.deleteProject(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(projectService, times(1)).deleteProject(1L);
    }
}
package fr.tmsconsult.myproject.task.service;

import fr.tmsconsult.myproject.task.dto.ProjectDto;
import fr.tmsconsult.myproject.task.mapper.ProjectMapper;
import fr.tmsconsult.myproject.task.model.Project;
import fr.tmsconsult.myproject.task.repository.ProjectRepository;
import fr.tmsconsult.myproject.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

     private final ProjectRepository projectRepository;
     private final ProjectMapper projectMapper;
     private final UserService userService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public ProjectDto saveProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        project.setCreatedAt(LocalDateTime.now());
        project.setLeader(userService.getConnectedUser());
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);

    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProjectById(id);
        if (project != null) {
            project.setName(projectDetails.getName());
            project.setLeader(projectDetails.getLeader());
            return projectRepository.save(project);
        }
        return null;
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project getProjectByName(String name) {
        return projectRepository.findByName(name);
    }
}
package fr.tmsconsult.myproject.task.service;

import fr.tmsconsult.myproject.security.service.UserService;
import fr.tmsconsult.myproject.shared.exception.NotFoundException;
import fr.tmsconsult.myproject.task.dto.SettingParameterTableLinkDto;
import fr.tmsconsult.myproject.task.dto.TaskDto;
import fr.tmsconsult.myproject.task.model.*;
import fr.tmsconsult.myproject.task.repository.SettingParameterRepository;
import fr.tmsconsult.myproject.task.repository.SettingTableRepository;
import fr.tmsconsult.myproject.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final SettingParameterRepository settingParameterRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final SettingTableRepository settingTableRepository;

    private final Logger logger = LoggerFactory.getLogger(TaskService.class);
    public Task saveTask(Task task) {
        return task;
    }

    public List<Task> getAllTasks() {
        logger.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {

        return taskRepository.findById(id).orElse(null);
    }

    public void deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task == null){
            throw new NotFoundException("Task id:"+id);
        }
            taskRepository.delete(task.get());

    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setDescription(taskDetails.getDescription());
            task.setProject(taskDetails.getProject());
            return taskRepository.save(task);
        }
        return null;
    }

    public void assignParameterToTask(Long taskId, Long parameterId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        SettingParameter param = settingParameterRepository.findById(parameterId).orElseThrow();

        TaskParameter link = new TaskParameter();
        link.setTask(task);
        link.setParameter(param);
        task.getParameters().add(link);

        taskRepository.save(task);
    }

   /* @Transactional
    public Task createTask(TaskDto dto) throws Throwable , RuntimeException{
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        //check if project exists
        if(projectService.getProjectByName(dto.getProjectName()) == null) {
            throw new RuntimeException("Project not found: " + dto.getProjectName());
        }else {
            Project project = projectService.getProjectByName(dto.getProjectName());
            task.setProject(project);
        }
        if(dto.getExpectedEndDate() != null) {
            task.setExpectedEndDate(LocalDate.parse(dto.getExpectedEndDate()));
        }
        if (dto.getEstimatedEndDate() != null) {
            task.setEstimatedEndDate(LocalDate.parse(dto.getEstimatedEndDate()));
        }
        if(dto.getEstimatedStartDate() != null) {
            task.setEstimatedStartDate(LocalDate.parse(dto.getEstimatedStartDate()));
        }


        // ⚙️ Traitement des paramètres dynamiques
        List<TaskParameter> taskParameters = new ArrayList<>();
        for (SettingParameterTableLinkDto paramDto : dto.getParameters()) {
            SettingTable table = (SettingTable) settingTableRepository.findByCode(paramDto.getTableCode())
                    .orElseThrow(() -> new RuntimeException("Table not found: " + paramDto.getTableCode()));

            SettingParameter param = settingParameterRepository.findByCodeAndSettingTable(paramDto.getParameterCode(), table)
                    .orElseThrow(() -> new RuntimeException("Parameter not found: " + paramDto.getParameterCode()));

            TaskParameter tp = new TaskParameter();
            tp.setTask(task);
            tp.setParameter(param);
            taskParameters.add(tp);
        }

        task.setParameters(taskParameters);
        return taskRepository.save(task);
    }*/

}

package fr.tmsconsult.myproject.task.service;
import fr.tmsconsult.myproject.security.model.User;
import fr.tmsconsult.myproject.security.repository.UserRepository;
import fr.tmsconsult.myproject.task.model.Project;
import fr.tmsconsult.myproject.task.model.Task;
import fr.tmsconsult.myproject.task.repository.ProjectRepository;
import fr.tmsconsult.myproject.task.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    private Task task;
    private Project project = Project.builder().name("Test Project").build();
    private User user = User.builder().username("Test User").build();

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(TaskServiceTest.class);
    @BeforeEach
    void setUp() {
        logger.info("Setting up test data");
        task  = Task.builder()
                .id(1L)
                .description("Test task")
                .build();
       /* userRepository.save(user);
        project.setLeader(user);
        projectRepository.save(project);
        task.setProject(project);
        taskRepository.save(task);*/

    }


    @Test
    void getAllTasks_withOneTask() {
        System.out.println("getAllTasks:"+task);
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> actualTasks = taskService.getAllTasks();
        System.out.println("actualTasks:"+actualTasks.get(0));

        assertNotNull(actualTasks);
        assertEquals(1, actualTasks.size());
        assertEquals(task, actualTasks.get(0));
    }

    @Test
    void getTaskById() {
        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task));

        Task actualTask = taskService.getTaskById(1L);

        assertNotNull(actualTask);
        assertEquals(task, actualTask);

    }
}

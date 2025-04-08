package fr.tmsconsult.myproject.task.controller;
import fr.tmsconsult.myproject.task.model.Project;
import fr.tmsconsult.myproject.task.repository.ProjectRepository;
import fr.tmsconsult.myproject.security.service.JwtServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    static Logger logger = LoggerFactory.getLogger(ProjectControllerTest.class);
    private Project project;
    private LocalDateTime NOW = LocalDateTime.now();
    public static final String PROJECT_NAME = "TMS";
    private String token;



    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        logger.info("Starting ProjectControllerTest");
    }

    @BeforeEach
    public void setUp() throws Exception {
        logger.info("ProjectControllerTest setUpBeforeEach");
        project = Project.builder().id(1L)
                .name(PROJECT_NAME)
                .createdAt(NOW).build();
        projectRepository.save(project);

        token = jwtServiceImpl.generateToken("testuser");


    }

    @Test
    @WithMockUser
    void test_getAllProjects() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/project")
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(PROJECT_NAME)));

    }


}

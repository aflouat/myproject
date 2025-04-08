package fr.tmsconsult.myproject.task.model;

import fr.tmsconsult.myproject.security.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;


    @ManyToOne
    private Project project;

    private LocalDate expectedEndDate;
    private LocalDate estimatedEndDate;
    private LocalDate estimatedStartDate;
    private LocalDateTime effectiveStartDateTime;
    private LocalDateTime effectiveEndDateTime;

    @ManyToOne
    private User assignee;

    private String title;

    // Relations dynamiques via task_parameters
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskParameter> parameters = new ArrayList<>();
}

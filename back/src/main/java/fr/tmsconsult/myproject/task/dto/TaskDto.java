package fr.tmsconsult.myproject.task.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String projectName;
    private String expectedEndDate;
    private String estimatedEndDate;
    private String estimatedStartDate;
    private String effectiveStartDateTime;
    private String effectiveEndDateTime;
    private String assigneeUsername;
     // Paramètres dynamiques liés à la tâche
    private List<SettingParameterTableLinkDto> parameters;
 }

package fr.tmsconsult.myproject.task.dto;

import lombok.*;

@Builder
@Data @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private String name;
    private String leaderUsername;
    private String createdAt;
    private String description;
    private String startDate;
    private String endDate;

}

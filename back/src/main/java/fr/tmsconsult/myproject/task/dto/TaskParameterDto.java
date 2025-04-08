package fr.tmsconsult.myproject.task.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskParameterDto {
    private String taskName;
    private String ParameterName;
}

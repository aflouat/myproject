package fr.tmsconsult.myproject.task.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettingParameterTableLinkDto {
    private String tableCode;     // ex: "CATEGORY"
    private String parameterCode; // ex: "BUG", "STORY", "HIGH"
}

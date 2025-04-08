package fr.tmsconsult.myproject.task.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter @Setter @NoArgsConstructor
public class TaskParameter {

    @EmbeddedId
    private TaskParameterId id = new TaskParameterId();

    @ManyToOne
    @MapsId("taskId")
    private Task task;

    @ManyToOne
    @MapsId("parameterId")
    private SettingParameter parameter;
}

package fr.tmsconsult.myproject.task.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class TaskParameterId implements Serializable {

    private Long taskId;
    private Long parameterId;

}
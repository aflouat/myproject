package fr.tmsconsult.myproject.task.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
     private Double value;

    @NotNull
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
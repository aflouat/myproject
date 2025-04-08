package fr.tmsconsult.myproject.task.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SettingTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code; // ex: "CATEGORY", "STATUS"

    private String label;

    @OneToMany(mappedBy = "settingTable", cascade = CascadeType.ALL)
    private List<SettingParameter> parameters = new ArrayList<>();
}


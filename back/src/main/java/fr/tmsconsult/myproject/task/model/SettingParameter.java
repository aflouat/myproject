package fr.tmsconsult.myproject.task.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class SettingParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;  // ex: "BUG", "EPIC"
    private String label; // ex: "Bug", "Epic"

    @ManyToOne
    @JoinColumn(name = "setting_table_id")
    private SettingTable settingTable;
}

package fr.tmsconsult.myproject.task.repository;

import fr.tmsconsult.myproject.task.model.SettingParameter;
import fr.tmsconsult.myproject.task.model.SettingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SettingParameterRepository extends JpaRepository<SettingParameter, Long> {

    Optional<SettingParameter> findByCodeAndSettingTable(String code, SettingTable settingTable);

}

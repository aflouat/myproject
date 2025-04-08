package fr.tmsconsult.myproject.task.repository;

import fr.tmsconsult.myproject.task.model.SettingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingTableRepository extends JpaRepository<SettingTable,Long> {
    Optional<SettingTable> findByCode(String code);
}

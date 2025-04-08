package fr.tmsconsult.myproject.task.repository;

import fr.tmsconsult.myproject.task.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}

package fr.tmsconsult.myproject.task.repository;

import fr.tmsconsult.myproject.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

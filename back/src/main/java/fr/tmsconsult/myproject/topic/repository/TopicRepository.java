package fr.tmsconsult.myproject.topic.repository;

import fr.tmsconsult.myproject.topic.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    public Topic findBySubject(String subject);
}

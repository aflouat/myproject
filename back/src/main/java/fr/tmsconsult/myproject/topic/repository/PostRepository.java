package fr.tmsconsult.myproject.topic.repository;

import fr.tmsconsult.myproject.topic.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long > {
    public List<Post> findByTopicSubject(final String topicSubject);
}

package fr.tmsconsult.myproject.topic.repository;

import fr.tmsconsult.myproject.topic.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findByPostId(Long postId);
}

package fr.tmsconsult.myproject.topic.repository;

import fr.tmsconsult.myproject.topic.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    Optional<Subscription> findByTopicIdAndUserId(Long topicId, Long userId);
    List<Subscription> findByUserId(Long userId);
}

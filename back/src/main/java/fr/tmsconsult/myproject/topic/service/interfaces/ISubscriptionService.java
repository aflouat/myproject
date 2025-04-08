package fr.tmsconsult.myproject.topic.service.interfaces;

import fr.tmsconsult.myproject.topic.dto.TopicDto;

import java.util.List;

public interface ISubscriptionService {
    public void subscribe(Long idTopic);
    public void unSubscribe(Long idTopic);
    List<TopicDto> getAllTopicsWithSubscriptionStatusForCurrentUser();
    public List<TopicDto> getAllSubscribedTopics();
}

package fr.tmsconsult.myproject.topic.service.interfaces;

import fr.tmsconsult.myproject.topic.dto.TopicDto;
import fr.tmsconsult.myproject.topic.model.Topic;

import java.util.List;

public interface ITopicService {
    public TopicDto create(TopicDto topic);
    public List<TopicDto> findAll();
    public Topic findById(Long id);
    public Topic findBySubject(String subject);
}

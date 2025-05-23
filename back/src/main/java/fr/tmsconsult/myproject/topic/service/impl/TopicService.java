package fr.tmsconsult.myproject.topic.service.impl;

import fr.tmsconsult.myproject.topic.dto.TopicDto;
import fr.tmsconsult.myproject.topic.exception.TopicNotFoundException;
import fr.tmsconsult.myproject.topic.mapper.TopicMapper;
import fr.tmsconsult.myproject.topic.model.Topic;
import fr.tmsconsult.myproject.topic.repository.TopicRepository;
import fr.tmsconsult.myproject.topic.service.interfaces.ITopicService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService implements ITopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper  topicMapper;

    Logger logger = LoggerFactory.getLogger(TopicService.class);

    public TopicDto create(TopicDto topicDto) {
        return topicMapper.toDto(
                topicRepository.save(topicMapper.toEntity(topicDto)));
    }
@Override
    public Topic findById(Long id) {
        return topicRepository.findById(id).orElse(null);
    }
    public List<TopicDto> findAll() {
        List<Topic> topics = topicRepository.findAll();
        List<TopicDto> topicDtoList = topics.stream().map(topicMapper::toDto).collect(Collectors.toList());

        return topicDtoList;
    }
    public Topic findBySubject(String subject) {
        Topic foundTopic = topicRepository.findBySubject( subject);
        if (foundTopic == null) {
            throw new TopicNotFoundException(subject);
        }
        return foundTopic;
    }
}

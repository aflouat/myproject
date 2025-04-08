package fr.tmsconsult.myproject.topic.controller;

import fr.tmsconsult.myproject.topic.dto.TopicDto;
import fr.tmsconsult.myproject.topic.mapper.TopicMapper;
import fr.tmsconsult.myproject.topic.service.interfaces.ISubscriptionService;
import fr.tmsconsult.myproject.topic.service.interfaces.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
@RequiredArgsConstructor
public class TopicController {

    private final ITopicService topicService;
    private final TopicMapper topicMapper;
    private final ISubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody final TopicDto topicDto) {
        return ResponseEntity.ok().body(topicService.create(topicDto));

    }

    @GetMapping
    public ResponseEntity<?> getAllTopics() {
        List<TopicDto> topicDtoList = subscriptionService.getAllTopicsWithSubscriptionStatusForCurrentUser();
        return ResponseEntity.ok().body(topicDtoList);
    }




}

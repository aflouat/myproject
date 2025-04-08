package fr.tmsconsult.myproject.topic.service.impl;

import fr.tmsconsult.myproject.topic.dto.TopicDto;
import fr.tmsconsult.myproject.topic.exception.TopicNotFoundException;
import fr.tmsconsult.myproject.topic.mapper.TopicMapper;
import fr.tmsconsult.myproject.topic.model.Subscription;
import fr.tmsconsult.myproject.topic.model.Topic;
import fr.tmsconsult.myproject.security.model.User;
import fr.tmsconsult.myproject.topic.repository.SubscriptionRepository;
import fr.tmsconsult.myproject.topic.repository.TopicRepository;
import fr.tmsconsult.myproject.topic.service.interfaces.ISubscriptionService;
import fr.tmsconsult.myproject.topic.service.interfaces.ITopicService;
import fr.tmsconsult.myproject.security.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService implements ISubscriptionService {

    private final ITopicService topicService;
    private final IUserService userService;
    private final SubscriptionRepository subscriptionRepository;
    private final TopicMapper topicMapper;
    private final TopicRepository topicRepository;

    Logger logger = LoggerFactory.getLogger(SubscriptionService.class);
    @Override
    public void subscribe(Long idTopic) {
        Topic foundTopic = topicService.findById(idTopic);
        if (foundTopic == null) {
            throw new TopicNotFoundException("topic not found");

        }
        User foundUser = userService.getConnectedUser();
        logger.debug("subscribeUserToTopic foundUser = {}", foundUser);
        if (foundUser == null) {
            throw new UsernameNotFoundException("user not found");
        }

        Optional<Subscription> foundSubscription = subscriptionRepository.findByTopicIdAndUserId(idTopic, foundUser.getId());
        if (!foundSubscription.isPresent()) {
            subscriptionRepository.save(Subscription.builder().id(null).topic(foundTopic).user(foundUser).build());
        }

    }

    @Override
    public void unSubscribe(Long idTopic) {
        Topic foundTopic = topicService.findById(idTopic);
        if (foundTopic == null) {
            throw new TopicNotFoundException("topic not found");

        }
        User foundUser = userService.getConnectedUser();

        Optional<Subscription> foundSubscription = subscriptionRepository.findByTopicIdAndUserId(idTopic, foundUser.getId());
        subscriptionRepository.delete(foundSubscription.get());

    }

    @Override
    public List<TopicDto> getAllTopicsWithSubscriptionStatusForCurrentUser() {
        User currentUser = userService.getConnectedUser();
        // Récupérer les IDs des topics auxquels l'utilisateur est abonné
        List<Long> subscribedTopicIds = subscriptionRepository.findByUserId(currentUser.getId())
                .stream()
                .map(subscription -> subscription.getTopic().getId())
                .toList();
        List<Topic> topicList = topicRepository.findAll();

        List<TopicDto> topicDtoList = topicList.stream().map(topic -> {
            TopicDto topicDto = topicMapper.toDto(topic);
            topicDto.setUserSubscribed(subscribedTopicIds.contains(topic.getId()));

            return topicDto;
        }).toList();

        return topicDtoList;
    }

    @Override
    public List<TopicDto> getAllSubscribedTopics() {
        User currentUser = userService.getConnectedUser();
        List<Subscription> subscriptionList = subscriptionRepository.findByUserId(currentUser.getId());

        return subscriptionList.stream()
                .map(subscription -> {

                    TopicDto topicDto = topicMapper.toDto(subscription.getTopic());
                    topicDto.setUserSubscribed(true);
                    return topicDto;
                })
                .toList();
    }


}

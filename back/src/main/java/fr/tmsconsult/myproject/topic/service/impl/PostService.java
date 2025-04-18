package fr.tmsconsult.myproject.topic.service.impl;

import fr.tmsconsult.myproject.topic.dto.PostDto;
import fr.tmsconsult.myproject.topic.dto.TopicDto;
import fr.tmsconsult.myproject.topic.mapper.PostMapper;
import fr.tmsconsult.myproject.topic.model.Post;
import fr.tmsconsult.myproject.topic.model.Topic;
import fr.tmsconsult.myproject.topic.repository.PostRepository;
import fr.tmsconsult.myproject.topic.service.interfaces.IPostService;
import fr.tmsconsult.myproject.topic.service.interfaces.ISubscriptionService;
import fr.tmsconsult.myproject.topic.service.interfaces.ITopicService;
import fr.tmsconsult.myproject.security.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {


    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final IUserService userService;
    private final ITopicService topicService;

    private final ISubscriptionService subscriptionService;

    Logger logger = LoggerFactory.getLogger(PostService.class);

    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
       return posts;
    }

    @Override
    public List<PostDto> findAllFeeds() {
        List<TopicDto> topicDtoList = subscriptionService.getAllTopicsWithSubscriptionStatusForCurrentUser();
        List<PostDto> postDtoList = new ArrayList<>();
        for (TopicDto topicDto : topicDtoList) {
            if (topicDto.isUserSubscribed()) {
                postDtoList.addAll(
                        postMapper.mapToPostDtoList(
                                postRepository.findByTopicSubject(
                                        topicDto.getSubject())));
            }
        }

        return postDtoList;
    }

    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        return postMapper.mapToPostDto(post);
    }

    public PostDto create(PostDto postDtoSave) {
        Topic foundTopic = topicService.findBySubject(postDtoSave.getTopicSubject());
        Post postToSave = this.postMapper.mapToPost(postDtoSave);
        postToSave.setAuthor(userService.getConnectedUser());
        postToSave.setTopic(foundTopic);
        postToSave.setCreatedAt(LocalDateTime.now());
        logger.debug("Creating post: {}", postToSave);

        Post savedPost=postRepository.save(postToSave);


         return postMapper.mapToPostDto(savedPost);
    }


    @Override
    public void createBulk(List<PostDto> postDtoList) {
        for (PostDto postDto : postDtoList) {
            this.create(postDto);
        }

    }

    @Override
    public Post findById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }
        return post;
    }


}

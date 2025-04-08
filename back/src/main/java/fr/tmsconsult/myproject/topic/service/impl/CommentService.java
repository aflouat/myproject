package fr.tmsconsult.myproject.topic.service.impl;

import fr.tmsconsult.myproject.security.service.UserService;
import fr.tmsconsult.myproject.topic.dto.CommentDto;
import fr.tmsconsult.myproject.topic.mapper.CommentMapper;
import fr.tmsconsult.myproject.topic.model.Post;
import fr.tmsconsult.myproject.topic.model.Comment;
import fr.tmsconsult.myproject.topic.repository.CommentRepository;
import fr.tmsconsult.myproject.topic.service.interfaces.IPostService;
import fr.tmsconsult.myproject.topic.service.interfaces.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final IPostService postService;
    private final CommentMapper commentMapper;
    private final UserService userService;

    @Override
    public void commentToPost(CommentDto commentDto) {
        Post post = postService.findById(commentDto.getPostId());
        Comment commentToBeSaved = commentMapper.toEntity(commentDto);
        commentToBeSaved.setAuthor(userService.getConnectedUser());
        commentToBeSaved.setPost(post);
        commentRepository.save(commentToBeSaved);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(comment -> commentMapper.toDto(comment))
                .collect(Collectors.toList());
    }
}
package fr.tmsconsult.myproject.topic.service.interfaces;

import fr.tmsconsult.myproject.topic.dto.CommentDto;

import java.util.List;

public interface ICommentService {
    public void commentToPost(CommentDto commentDto) ;
    public List<CommentDto> getAllCommentsByPostId(Long postId);
}

package fr.tmsconsult.myproject.topic.mapper;

import fr.tmsconsult.myproject.topic.dto.CommentDto;
import fr.tmsconsult.myproject.topic.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
        CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

        @Mapping(target ="postId" ,source="post.id")
        @Mapping(target="username",source ="author.username" )
        CommentDto toDto(Comment comment);

        Comment toEntity(CommentDto commentDto);

        List<CommentDto> toDtoList(List<Comment> comments);
    }

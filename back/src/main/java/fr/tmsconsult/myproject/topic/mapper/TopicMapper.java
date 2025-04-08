package fr.tmsconsult.myproject.topic.mapper;

import fr.tmsconsult.myproject.topic.dto.TopicDto;
import fr.tmsconsult.myproject.topic.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    TopicDto toDto(Topic topic);

    Topic toEntity(TopicDto topicDto);
}

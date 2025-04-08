package fr.tmsconsult.myproject.security.mapper;


import fr.tmsconsult.myproject.security.dto.UserDto;
import fr.tmsconsult.myproject.security.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}

package fr.tmsconsult.myproject.task.mapper;

import fr.tmsconsult.myproject.task.dto.ProjectDto;
import fr.tmsconsult.myproject.task.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target="leaderUsername",source ="leader.username" )
    @Mapping(target="startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target="endDate", dateFormat = "yyyy-MM-dd")

    ProjectDto toDto(Project project);

    Project toEntity(ProjectDto projectDto);

    List<ProjectDto> toDtoList(List<Project> projects);

}

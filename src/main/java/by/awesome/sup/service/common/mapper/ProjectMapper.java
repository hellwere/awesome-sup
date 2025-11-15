package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.project.ProjectDto;
import by.awesome.sup.entity.common.project.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto toDto(Project project);
    Project toEntity(ProjectDto projectDto);
}

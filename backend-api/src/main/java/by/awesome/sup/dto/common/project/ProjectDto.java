package by.awesome.sup.dto.common.project;

import by.awesome.sup.dto.authorization.UserDto;
import by.awesome.sup.dto.common.task.TaskDto;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.project.Status;
import by.awesome.sup.entity.common.task.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectDto {
    Long id;
    Status status;
    List<Tag> tags = new ArrayList<>();
    LocalDateTime creationAt;
    Integer estimate;
    Priority priority;
    List<TaskDto> tasks = new ArrayList<>();
    @Schema(description = "Список пользователей проекта", hidden = true)
    List<UserDto> users = new ArrayList<>();
}

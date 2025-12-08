package by.awesome.sup.dto.common.project;

import by.awesome.sup.dto.authorization.UserDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoRequest;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.project.Status;
import by.awesome.sup.entity.common.task.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectDtoRequest {
    Long id;
    @NotNull
    Status status;
    List<Tag> tags = new ArrayList<>();
    Integer estimate;
    @NotNull
    Priority priority;
}

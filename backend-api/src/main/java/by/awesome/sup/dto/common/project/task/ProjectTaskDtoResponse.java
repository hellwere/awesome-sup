package by.awesome.sup.dto.common.project.task;

import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.task.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectTaskDtoResponse {
    Long id;
    String name;
    Status status;
    Priority priority;
}

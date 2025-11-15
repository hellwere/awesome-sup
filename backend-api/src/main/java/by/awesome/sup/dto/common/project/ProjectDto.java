package by.awesome.sup.dto.common.project;

import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.project.Status;
import by.awesome.sup.entity.common.task.Tag;
import by.awesome.sup.entity.common.task.Task;
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
    List<Task> tasks = new ArrayList<>();
    List <User> users = new ArrayList<>();
}

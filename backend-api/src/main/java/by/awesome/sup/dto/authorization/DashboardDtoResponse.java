package by.awesome.sup.dto.authorization;

import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.dto.common.task.TaskDtoResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DashboardDtoResponse {
    List<ProjectDtoResponse> projects = new ArrayList<>();
    List<TaskDtoResponse> tasks = new ArrayList<>();
}

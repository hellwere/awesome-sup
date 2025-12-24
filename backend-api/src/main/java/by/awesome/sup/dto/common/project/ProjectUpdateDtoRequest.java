package by.awesome.sup.dto.common.project;

import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.project.Status;
import by.awesome.sup.entity.common.task.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectUpdateDtoRequest {
    @NotNull
    Long id;
    @NotBlank
    String name;
    @NotNull
    Status status;
    @Valid
    List<Tag> tags = new ArrayList<>();
    Integer estimate;
    @NotNull
    Priority priority;
}

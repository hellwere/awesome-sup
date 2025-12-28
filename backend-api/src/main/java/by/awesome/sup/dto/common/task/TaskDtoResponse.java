package by.awesome.sup.dto.common.task;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.task.Status;
import by.awesome.sup.entity.common.task.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDtoResponse {
    Long id;
    String name;
    @NotNull
    Status status;
    List<Tag> tags = new ArrayList<>();
    LocalDateTime creationAt;
    Integer estimate;
    @NotNull
    Priority priority;
    List<AttachmentDtoResponse> attachment;
    List<CommentDtoResponse> comments = new ArrayList<>();
    List<TimesheetDtoResponse> timesheets = new ArrayList<>();
}

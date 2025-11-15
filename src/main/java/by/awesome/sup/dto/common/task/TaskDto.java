package by.awesome.sup.dto.common.task;

import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.Timesheet;
import by.awesome.sup.entity.common.task.Status;
import by.awesome.sup.entity.common.task.Tag;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {
    Long id;
    Status status;
    List<Tag> tags = new ArrayList<>();
    LocalDateTime creationAt;
    Integer estimate;
    Priority priority;
    Attachment attachment;
    List<Timesheet> timesheets = new ArrayList<>();
}

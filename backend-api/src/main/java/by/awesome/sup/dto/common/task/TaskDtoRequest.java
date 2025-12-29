package by.awesome.sup.dto.common.task;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.task.Status;
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
public class TaskDtoRequest {
    @NotBlank
    String name;
    @NotNull
    Status status;
    @Valid
    List<Tag> tags = new ArrayList<>();
    Integer estimate;
    @NotNull
    Priority priority;
    @Valid
    List<AttachmentDtoRequest> attachments = new ArrayList<>();
    List<CommentDtoResponse> comments = new ArrayList<>();
    List<String> userList = new ArrayList<>();
}

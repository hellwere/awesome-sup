package by.awesome.sup.entity.common.task;

import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.Timesheet;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(schema = "sup", name = "task")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {

    @Id
    @SequenceGenerator(name = "taskSq", sequenceName = "task_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "taskSq", strategy = GenerationType.SEQUENCE)
    Long id;
    @Enumerated(EnumType.STRING)
    Status status;
    @Enumerated(EnumType.STRING)
    List<Tag> tags = new ArrayList<>();
    @Column(name = "creation_at")
    LocalDateTime creationAt;
    Integer estimate;
    @Enumerated(EnumType.STRING)
    Priority priority;

    @OneToMany
    @JoinColumn(name = "attachment_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Attachment attachment;

    @OneToMany
    @JoinColumn(name = "timesheet_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Timesheet> timesheets = new ArrayList<>();
}

package by.awesome.sup.entity.common;

import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.task.Task;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(schema = "sup", name = "timesheet")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Timesheet {

    @Id
    @SequenceGenerator(name = "timesheetSq", sequenceName = "timesheet_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "timesheetSq", strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(name = "logged_time")
    Double loggedTime;
    @Column(nullable = false, updatable = false)
    String owner;

    @UpdateTimestamp
    @Column(name = "logged_at", nullable = false, updatable = false)
    LocalDateTime loggedAt;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

    @ManyToOne
    @JoinColumn(name = "task_id")
    Task task;

}

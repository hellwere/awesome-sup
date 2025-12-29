package by.awesome.sup.entity.attachment;

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
@Table(schema = "sup", name = "attachment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attachment {

    @Id
    @SequenceGenerator(name = "attachmentSq", sequenceName = "attachment_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "attachmentSq", strategy = GenerationType.SEQUENCE)
    Long id;
    String format;
    Long length;
    @Column(nullable = false, updatable = false)
    String owner;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    File file;
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

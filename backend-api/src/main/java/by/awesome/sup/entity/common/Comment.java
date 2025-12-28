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
@Table(schema = "sup", name = "comment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {

    @Id
    @SequenceGenerator(name = "commentSq", sequenceName = "comment_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "commentSq", strategy = GenerationType.SEQUENCE)
    Long id;
    String data;
    @Column(nullable = false)
    String owner;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
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

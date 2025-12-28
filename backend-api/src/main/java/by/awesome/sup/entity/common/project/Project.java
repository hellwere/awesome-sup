package by.awesome.sup.entity.common.project;

import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.entity.common.Comment;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.Timesheet;
import by.awesome.sup.entity.common.task.Tag;
import by.awesome.sup.entity.common.task.Task;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(schema = "sup", name = "project")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {

    @Id
    @SequenceGenerator(name = "projectSq", sequenceName = "project_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "projectSq", strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(nullable = false)
    String name;
    @Enumerated(EnumType.STRING)
    Status status;
    @Enumerated(EnumType.STRING)
    List<Tag> tags = new ArrayList<>();
    @CreationTimestamp
    @Column(name = "creation_at", nullable = false, updatable = false)
    LocalDateTime creationAt;
    int estimate;
    @Enumerated(EnumType.STRING)
    Priority priority;
    @Column(nullable = false)
    String owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Task> tasks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Attachment> attachments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Comment> comments = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "project_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Timesheet> timesheets = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_members", schema = "sup",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<User> users = new ArrayList<>();
}

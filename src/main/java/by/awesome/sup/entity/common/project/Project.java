package by.awesome.sup.entity.common.project;

import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.task.Tag;
import by.awesome.sup.entity.common.task.Task;
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
@Table(schema = "sup", name = "project")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {

    @Id
    @SequenceGenerator(name = "projectSq", sequenceName = "project_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "projectSq", strategy = GenerationType.SEQUENCE)
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
    @JoinColumn(name = "task_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Task> tasks = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "project_members",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List <User> users = new ArrayList<>();
}

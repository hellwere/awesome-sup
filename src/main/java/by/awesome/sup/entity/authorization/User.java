package by.awesome.sup.entity.authorization;

import by.awesome.sup.entity.common.project.Project;
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
@Table(schema = "sup", name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @SequenceGenerator(name = "permissionSq", sequenceName = "permission_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "permissionSq", strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    String login;
    String password;
    String email;
    @Column(name = "creation_at")
    LocalDateTime creationAt;

    @OneToMany
    @JoinColumn(name = "permission_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Permission> permissions = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Project> projects = new ArrayList<>();
}

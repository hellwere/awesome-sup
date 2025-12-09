package by.awesome.sup.entity.authorization;

import by.awesome.sup.entity.common.project.Project;
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
@Table(schema = "sup", name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @SequenceGenerator(name = "userSq", sequenceName = "user_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "userSq", strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    String login;
    String password;
    String email;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime creationAt;

    /*@ManyToMany
    @JoinTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Permission> permissions = new ArrayList<>();*/

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Project> projects = new ArrayList<>();
}

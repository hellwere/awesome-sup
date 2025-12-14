package by.awesome.sup.entity.authorization;

import by.awesome.sup.entity.common.project.Project;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(schema = "sup", name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "userSq", sequenceName = "user_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "userSq", strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    @Column(nullable = false, unique = true)
    String login;
    String password;
    @Column(nullable = false, unique = true)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.login;
    }
}

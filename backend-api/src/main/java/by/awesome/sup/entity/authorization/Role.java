package by.awesome.sup.entity.authorization;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(schema = "sup", name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @SequenceGenerator(name = "rolesSq", sequenceName = "roles_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "rolesSq", strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(nullable = false, unique = true)
    String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Permission> permissions = new ArrayList<>();
}

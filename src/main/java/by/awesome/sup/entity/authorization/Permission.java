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
@Table(schema = "sup", name = "permission")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {

    @Id
    @SequenceGenerator(name = "permissionSq", sequenceName = "permission_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "permissionSq", strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    String grants;

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<User> userId = new ArrayList<>();
}

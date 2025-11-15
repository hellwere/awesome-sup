package by.awesome.sup.entity.common;

import by.awesome.sup.entity.authorization.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @Column(name = "user_id")
    User userId;
    String data;
}

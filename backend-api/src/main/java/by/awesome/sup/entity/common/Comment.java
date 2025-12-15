package by.awesome.sup.entity.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}

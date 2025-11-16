package by.awesome.sup.entity.attachment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Entity uses for storage content and update from attachment.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(schema = "sup", name = "file")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class File {

    @Id
    @SequenceGenerator(name = "fileSq", sequenceName = "file_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "fileSq", strategy = GenerationType.SEQUENCE)
    Long id;
    @Lob
    byte[] data;
}

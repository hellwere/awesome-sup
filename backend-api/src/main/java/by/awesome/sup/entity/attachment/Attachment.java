package by.awesome.sup.entity.attachment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(schema = "sup", name = "attachment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attachment {

    @Id
    @SequenceGenerator(name = "attachmentSq", sequenceName = "attachment_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "attachmentSq", strategy = GenerationType.SEQUENCE)
    Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    File file;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}

package by.awesome.sup.entity.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(schema = "sup", name = "timesheet")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Timesheet {

    @Id
    @SequenceGenerator(name = "timesheetSq", sequenceName = "timesheet_sq", schema = "sup", allocationSize = 1)
    @GeneratedValue(generator = "timesheetSq", strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(name = "logged_time")
    Double loggedTime;
    @CreationTimestamp
    @Column(name = "logged_at", nullable = false, updatable = false)
    LocalDateTime loggedAt;
}

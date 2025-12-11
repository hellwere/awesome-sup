package by.awesome.sup.repository;

import by.awesome.sup.entity.common.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
}

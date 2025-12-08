package by.awesome.sup.repository;

import by.awesome.sup.entity.common.Timesheet;
import org.springframework.data.repository.CrudRepository;

public interface TimesheetRepository extends CrudRepository<Timesheet, Long> {
}

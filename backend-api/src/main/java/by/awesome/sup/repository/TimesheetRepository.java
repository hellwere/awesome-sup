package by.awesome.sup.repository;

import by.awesome.sup.entity.common.Timesheet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetRepository extends CrudRepository<Timesheet, Long> {
}

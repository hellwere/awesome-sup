package by.awesome.sup.repository;

import by.awesome.sup.entity.common.Timesheet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByOwner(String owner, Pageable pageable);
}

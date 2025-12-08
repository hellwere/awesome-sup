package by.awesome.sup.repository;

import by.awesome.sup.entity.common.project.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}

package by.awesome.sup.repository;

import by.awesome.sup.entity.common.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

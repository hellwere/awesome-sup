package by.awesome.sup.repository;

import by.awesome.sup.entity.common.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner(String owner);
    List<Project> findByName(String name);
}

package by.awesome.sup.integration.feign;

import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "project-service", url = "http://localhost:8080")
public interface ProjectClient {

    @PostMapping
    ProjectDtoRequest getProjectById(Long id);
}

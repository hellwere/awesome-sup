package by.awesome.sup.controller;

import by.awesome.sup.dto.authorization.RoleDtoRequest;
import by.awesome.sup.dto.authorization.RoleDtoResponse;
import by.awesome.sup.service.authorization.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService service;

    @GetMapping
    public List<RoleDtoResponse> get(@RequestParam(required = false) String name, Integer page) {
        if (StringUtils.hasLength(name)) {
            return service.findByName(name);
        } else {
            return service.findAll(page);
        }
    }

    @GetMapping("/{id}")
    public RoleDtoResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public RoleDtoResponse add(@Valid @RequestBody RoleDtoRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public RoleDtoResponse update(@PathVariable Long id, @RequestBody RoleDtoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public RoleDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }
}

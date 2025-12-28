package by.awesome.sup.controller;

import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.service.common.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService service;

    /*@GetMapping
    public CommentDtoResponse get(@RequestParam Long projectId) {
        return service.findAll(projectId);
    }*/

    /*@GetMapping("/{id}")
    public CommentDtoResponse get(@PathVariable Long id) {
        return service.findById(id);
    }*/

    @PutMapping("/{id}")
    public CommentDtoResponse update(@PathVariable Long id, @Valid @RequestBody CommentDtoRequest commentDtoRequest) {
        return service.update(id, commentDtoRequest);
    }

    @DeleteMapping("/{id}")
    public CommentDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }

}

package by.awesome.sup.controller;

import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.service.common.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService service;

    @GetMapping("/{id}")
    public CommentDtoResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CommentDtoResponse add(@RequestParam String entityType, @RequestParam Long id,
                                  @Valid @RequestBody CommentDtoRequest commentDtoRequest) throws IOException {
        return service.add(entityType, id, commentDtoRequest);
    }

    @DeleteMapping("/{id}")
    public CommentDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }
}

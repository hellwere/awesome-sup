package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.entity.common.Comment;
import by.awesome.sup.repository.CommentRepository;
import by.awesome.sup.service.common.mapper.CommentMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentMapper mapper;
    CommentService service;
    CommentRepository repository = Mockito.mock(CommentRepository.class);


    @PostConstruct
    private void init() {
        service = new CommentService(repository, mapper);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(User
                .withUsername("login")
                .password("password")
                .build(), null));
    }

    @Test
    void findById() {
        CommentDtoRequest commentDtoRequest = new CommentDtoRequest();
        commentDtoRequest.setData("MyComment");
        Comment comment = mapper.toEntity(commentDtoRequest);
        when(repository.findById(1L)).thenReturn(Optional.of(comment));
        CommentDtoResponse commentDtoResponse = service.findById(1L);
        assertEquals(mapper.toDto(comment), commentDtoResponse);
    }
}
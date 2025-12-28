package by.awesome.sup.service.attachment;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.attachment.FileDtoRequest;
import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.repository.AttachmentRepository;
import by.awesome.sup.service.attachment.mapper.AttachmentMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class AttachmentServiceTest {

    @Autowired
    AttachmentMapper mapper;
    AttachmentService service;
    AttachmentRepository repository = Mockito.mock(AttachmentRepository.class);


    @PostConstruct
    private void init() {
        service = new AttachmentService(repository, mapper);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(User
                .withUsername("login")
                .password("password")
                .build(), null));
    }

    @Test
    void add() {
        Project project = new Project();
        AttachmentDtoRequest attachmentDtoRequest = new AttachmentDtoRequest();
        FileDtoRequest fileDtoRequest = new FileDtoRequest();
        fileDtoRequest.setData("FILE".getBytes());
        attachmentDtoRequest.setFile(fileDtoRequest);
        AttachmentDtoResponse attachmentDtoResponse = service.add(project, attachmentDtoRequest);
        Attachment attachment = mapper.toEntity(attachmentDtoRequest);
        assertEquals(mapper.toDto(attachment), attachmentDtoResponse);
        assertFalse(project.getAttachments().isEmpty());
    }

    @Test
    void testAdd() {
        Task task = new Task();
        AttachmentDtoRequest attachmentDtoRequest = new AttachmentDtoRequest();
        FileDtoRequest fileDtoRequest = new FileDtoRequest();
        fileDtoRequest.setData("FILE".getBytes());
        attachmentDtoRequest.setFile(fileDtoRequest);
        AttachmentDtoResponse attachmentDtoResponse = service.add(task, attachmentDtoRequest);
        Attachment attachment = mapper.toEntity(attachmentDtoRequest);
        assertEquals(mapper.toDto(attachment), attachmentDtoResponse);
        assertFalse(task.getAttachments().isEmpty());
    }
}
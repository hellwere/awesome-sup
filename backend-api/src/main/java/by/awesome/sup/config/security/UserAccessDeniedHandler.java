package by.awesome.sup.config.security;

import by.awesome.sup.exceptions.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(403);
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(ErrorResponse.builder().code(403).message(accessDeniedException.getMessage()).build()));
    }
}

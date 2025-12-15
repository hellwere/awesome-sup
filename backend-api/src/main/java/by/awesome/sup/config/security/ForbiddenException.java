package by.awesome.sup.config.security;

import by.awesome.sup.exceptions.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class ForbiddenException implements AuthenticationEntryPoint {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(403);
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(ErrorResponse.builder().code(403).message(authException.getMessage()).build()));
    }
}

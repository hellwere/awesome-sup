package by.awesome.sup.interceptors.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;


@Slf4j
//@Component
public class MDCOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        MDC.put("transactionId", "- UID: " + UUID.randomUUID());
        MDC.put("requestURI", "- URI: " + request.getRequestURI());
        chain.doFilter(request, response);
    }
}

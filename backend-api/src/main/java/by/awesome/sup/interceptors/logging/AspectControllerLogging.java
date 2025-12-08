package by.awesome.sup.interceptors.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AspectControllerLogging {

    @Pointcut("within(by.awesome.sup.controller..*)")
    public void controllerMethods() {}

    @Before("execution(* by.awesome.sup.controller.*.*(..))")
    public void log(JoinPoint point) {
        log.info("Received request {}", Arrays.toString(point.getArgs()));
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logResponse(Object result) {
        log.info("Sent response {}", result);
    }

    @AfterThrowing(pointcut = "controllerMethods()", throwing = "ex")
    public void logError(Exception ex) {
        log.error("Error processing {}", ex.getMessage());
    }
}

package com.ManagerService.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect // Marks this class as an Aspect for AOP (Aspect-Oriented Programming)
@Component // Marks this class as a Spring component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut that matches all methods in ManagerServiceImpl class
    @Pointcut("execution(* com.ManagerService.service.ManagerServiceImpl.*(..))")
    public void serviceMethods() {
    }

    // Advice that logs before the execution of any method matched by the pointcut
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("A method in ManagerServiceImpl is about to be executed. Method: {}, Arguments: {}",
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    // Advice that logs after the successful execution of any method matched by the pointcut
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("A method in ManagerServiceImpl has executed successfully. Method: {}, Result: {}",
                joinPoint.getSignature().getName(), result);
    }

    // Advice that logs after an exception is thrown by any method matched by the pointcut
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("An error occurred in ManagerServiceImpl. Method: {}, Error: {}", joinPoint.getSignature().getName(),
                error.getMessage());
    }
}

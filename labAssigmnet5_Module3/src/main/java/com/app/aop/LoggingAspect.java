package com.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.app.service.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint jp) {
        logger.info("Before method: {}", jp.getSignature());
    }

    @After("serviceMethods()")
    public void logAfter(JoinPoint jp) {
        logger.info("After method: {}", jp.getSignature());
    }

    @AfterReturning(
        value = "execution(* com.app.service.BookService.getBook(..))",
        returning = "result"
    )
    public void logReturn(Object result) {
        logger.info("Returned value: {}", result);
    }

    @AfterThrowing(
        value = "serviceMethods()",
        throwing = "ex"
    )
    public void logException(Exception ex) {
        logger.error("Exception thrown: {}", ex.getMessage());
    }

    @Around("serviceMethods()")
    public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();

        logger.info(
            "Execution Time of {} : {} ms",
            pjp.getSignature(),
            (end - start)
        );
        return result;
    }
}

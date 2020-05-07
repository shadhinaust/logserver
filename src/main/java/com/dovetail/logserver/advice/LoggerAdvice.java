package com.dovetail.logserver.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerAdvice.class);

    private void execBeforeLogger(JoinPoint joinPoint) {
        LOG.info("Invoking: " + joinPoint.getSignature().toString());
    }

    private Object execAroundLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object response = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        LOG.info("Result fetched in " + (endTime - startTime) + " milliseconds.");
        return response;
    }

    private void execAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
        LOG.info("Exception occurred: " + ex.getMessage());
        LOG.error(joinPoint.getSignature().toString() + "\nException Description: " + ex);
    }

    @Before("execution(* com.dovetail.logserver.controller.*.*(..))")
    public void controllerLoggerBefore(JoinPoint joinPoint) {
        execBeforeLogger(joinPoint);
    }

    @Around("execution(* com.dovetail.logserver.controller.*.*(..))")
    public Object controllerLoggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return execAroundLogger(proceedingJoinPoint);
    }

    @AfterThrowing(value = "execution(* com.dovetail.logserver.controller.*.*(..))", throwing = "ex")
    public void controllerLoggerAfter(JoinPoint joinPoint, Exception ex) {
        execAfterThrowingLogger(joinPoint, ex);
    }

    @Before("execution(* com.dovetail.logserver.service.*.*(..))")
    public void serviceLoggerBefore(JoinPoint joinPoint) {
        execBeforeLogger(joinPoint);
    }

    @Around("execution(* com.dovetail.logserver.service.*.*(..))")
    public Object serviceLoggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return execAroundLogger(proceedingJoinPoint);
    }

    @AfterThrowing(value = "execution(* com.dovetail.logserver.service.*.*(..))", throwing = "ex")
    public void serviceLoggerAfter(JoinPoint joinPoint, Exception ex) {
        execAfterThrowingLogger(joinPoint, ex);
    }

    @Before("execution(* com.dovetail.logserver.repository.*.*(..))")
    public void repositoryLoggerBefore(JoinPoint joinPoint) {
        execBeforeLogger(joinPoint);
    }

    @Around("execution(* com.dovetail.logserver.repository.*.*(..))")
    public Object repositoryLoggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return execAroundLogger(proceedingJoinPoint);
    }

    @AfterThrowing(value = "execution(* com.dovetail.logserver.repository.*.*(..))", throwing = "ex")
    public void repositoryLoggerAfter(JoinPoint joinPoint, Exception ex) {
        execAfterThrowingLogger(joinPoint, ex);
    }
}
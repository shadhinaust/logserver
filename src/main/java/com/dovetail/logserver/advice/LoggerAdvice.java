package com.dovetail.logserver.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerAdvice.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private void execBeforeLogger(JoinPoint joinPoint) {
        LOG.info("Invoking: " + joinPoint.getSignature().toString());
    }

    private Object execAroundLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        LOG.info("Entered: " + className + " : " + methodName + "(" + MAPPER.writeValueAsString(args) + ")");
        Object response = proceedingJoinPoint.proceed();
        LOG.info("Returned: " + className + " : " + methodName + "()" + " response : " + MAPPER.writeValueAsString(response));
        return response;
    }

    private void execAfterLogger(JoinPoint joinPoint) {
        LOG.info("Invoked: " + joinPoint.getSignature().toString());
    }

    @Before("execution(* com.dovetail.logserver.controller.*.*(..))")
    public void controllerLoggerBefore(JoinPoint joinPoint) {
        execBeforeLogger(joinPoint);
    }

    @Around("execution(* com.dovetail.logserver.controller.*.*(..))")
    public Object controllerLoggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return execAroundLogger(proceedingJoinPoint);
    }

    @After("execution(* com.dovetail.logserver.controller.*.*(..))")
    public void controllerLoggerAfter(JoinPoint joinPoint) {
        execAfterLogger(joinPoint);
    }

    @Before("execution(* com.dovetail.logserver.service.*.*(..))")
    public void serviceLoggerBefore(JoinPoint joinPoint) {
        execBeforeLogger(joinPoint);
    }

    @Around("execution(* com.dovetail.logserver.service.*.*(..))")
    public Object serviceLoggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return execAroundLogger(proceedingJoinPoint);
    }

    @After("execution(* com.dovetail.logserver.service.*.*(..))")
    public void serviceLoggerAfter(JoinPoint joinPoint) {
        execAfterLogger(joinPoint);
    }

    @Before("execution(* com.dovetail.logserver.repository.*.*(..))")
    public void repositoryLoggerBefore(JoinPoint joinPoint) {
        execBeforeLogger(joinPoint);
    }

    @Around("execution(* com.dovetail.logserver.repository.*.*(..))")
    public Object repositoryLoggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return execAroundLogger(proceedingJoinPoint);
    }

    @After("execution(* com.dovetail.logserver.repository.*.*(..))")
    public void repositoryLoggerAfter(JoinPoint joinPoint) {
        execAfterLogger(joinPoint);
    }
}

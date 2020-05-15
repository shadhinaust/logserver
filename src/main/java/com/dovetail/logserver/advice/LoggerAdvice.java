package com.dovetail.logserver.advice;

import com.dovetail.logserver.log.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAdvice {

    @Autowired
    private Log log;

    @Pointcut("@annotation(logger)")
    public void callLogger(Logger logger) {
    }

/*	@Before(value = "callLogger(logger)")
	public void beforeLog(JoinPoint joinPoint, Logger logger) throws Throwable {
		log.log(logger.type(), logger.level(), logger.message());
	}*/

    @AfterReturning(value = "callLogger(logger)", returning = "returningObj")
    public void afterReturningLog(JoinPoint joinPoint, Logger logger, Object returningObj) {
        log.log(logger.type(), logger.level(), logger.message());
    }

    @AfterThrowing(value = "callLogger(logger)", throwing = "ex")
    public void afterThrowingLog(JoinPoint joinPoint, Logger logger, Exception ex) {
        log.error(logger.type(), " Exception: " + ex);
    }
}
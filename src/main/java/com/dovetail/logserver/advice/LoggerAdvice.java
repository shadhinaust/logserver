package com.dovetail.logserver.advice;

import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
@Component
public class LoggerAdvice {

	private void printLog(Logger logger) {
		ThreadContext.put("logType", logger.type().getLevel());
		switch (logger.level()) {
		case FATAL:
			log.fatal(logger.message());
			break;
		case ERROR:
			log.error(logger.message());
			break;
		case WARN:
			log.warn(logger.message());
			break;
		case INFO:
			log.info(logger.message());
			break;
		case DEBUG:
			log.debug(logger.message());
			break;
		case TRACE:
			log.trace(logger.message());
			break;
		}
	}

	@Pointcut("@annotation(logger)")
	public void callLogger(Logger logger) {
	}

	/*
	@Before(value = "callLogger(logger)")
	public void beforeLog(JoinPoint joinPoint, Logger logger) throws Throwable {
		printLog(logger);
	}
	*/

	@AfterReturning(value = "callLogger(logger)", returning = "returningObj")
	public void afterReturningLog(JoinPoint joinPoint, Logger logger, Object returningObj) throws Throwable {
		printLog(logger);
	}

	@AfterThrowing(value = "callLogger(logger)", throwing = "ex")
	public void afterThrowingLog(JoinPoint joinPoint, Logger logger, Exception ex) {
		ThreadContext.put("logType", logger.type().getLevel());
		log.error(" Exception: " + ex);
	}
}
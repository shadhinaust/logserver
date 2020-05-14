package com.dovetail.logserver.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dovetail.logserver.model.LogLevel;
import com.dovetail.logserver.model.LogType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Logger {
	
	public LogType type() default LogType.EVENT;

	public LogLevel level() default LogLevel.INFO;

	public String message();
}

package com.dovetail.logserver.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.dovetail.logserver.model.LogLevel;

@Converter
public class LogLevelConverter implements AttributeConverter<LogLevel, Integer> {

	@Override
	public Integer convertToDatabaseColumn(LogLevel logLevel) {
		return logLevel == null ? null : logLevel.getId();
	}

	@Override
	public LogLevel convertToEntityAttribute(Integer id) {
		return id == null ? null : LogLevel.getById(id);
	}
}

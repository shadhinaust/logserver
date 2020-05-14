package com.dovetail.logserver.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.dovetail.logserver.model.LogType;

@Converter
public class LogTypeConverter implements AttributeConverter<LogType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(LogType logType) {
		return logType == null ? null : logType.getId();
	}

	@Override
	public LogType convertToEntityAttribute(Integer id) {
		return id == null ? null : LogType.getById(id);
	}
}

package com.dovetail.logserver.converter;

import com.dovetail.logserver.dto.ApplicationLogDto;
import com.dovetail.logserver.model.ApplicationLog;
import com.dovetail.logserver.utils.DateTimeUtils;

public class ApplicationLogDataConverter {
	public static ApplicationLogDto convertApplicationLog(ApplicationLog log) {
		return ApplicationLogDto.builder().dateTime(DateTimeUtils.toDateTimeString(log.getDateTime()))
				.type(log.getType().getLevel()).level(log.getLevel().getStdLevel()).loggerName(log.getLoggerName())
				.message(log.getMessage()).exception(log.getException()).build();
	}
}

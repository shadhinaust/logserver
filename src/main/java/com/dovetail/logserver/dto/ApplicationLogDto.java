package com.dovetail.logserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicationLogDto {
	private String dateTime;
	private String type;
	private String level;
	private String loggerName;
	private String message;
	private String exception;
}

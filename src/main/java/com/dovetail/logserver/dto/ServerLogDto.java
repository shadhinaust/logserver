package com.dovetail.logserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServerLogDto {
	private String id;
	private String dateTime;
	private String message;
	private String format;
	private Long duration;
}

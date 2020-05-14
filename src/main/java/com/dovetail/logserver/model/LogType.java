package com.dovetail.logserver.model;

import java.util.Arrays;
import java.util.Objects;

import com.dovetail.logserver.exception.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogType {
	SYSTEM(1, "SYSTEM"), EVENT(2, "EVENT");

	private int id;
	private String level;

	public static LogType getById(int id) {
		return Arrays.stream(LogType.values())
				.filter(type -> Objects.equals(type.getId(), id))
				.findFirst()
				.orElseThrow(NotFoundException::new);
	}
}
package com.dovetail.logserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "application_log")
public class ApplicationLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "date_time")
	private LocalDateTime dateTime;

	@Column(name = "type")
	private LogType type;

	@Column(name = "level")
	private LogLevel level;

	@Column(name = "logger_name")
	private String loggerName;

	@Column(name = "message")
	private String message;

	@Column(name = "exception")
	private String exception;
}

package com.dovetail.logserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "server_logs")
public class ServerLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "date_time")
	private LocalDateTime dateTime;

	@Column(name = "message")
	private String message;

	@Column(name = "format")
	private String format;

	@Column(name = "duration")
	private Long duration;

	@Builder
	public ServerLog(String message, String format, Long duration) {
		this.message = message;
		this.format = format;
		this.duration = duration;
		this.dateTime = LocalDateTime.now();
	}
}

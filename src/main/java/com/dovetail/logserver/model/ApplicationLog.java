package com.dovetail.logserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_log")
public class ApplicationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
    
    @Column(name = "level")
    private String level;

    @Column(name = "log_type")
    private String logType;
    
    @Column(name = "logger_name")
    private String loggerName;

    @Column(name = "message")
    private String message;
    
    @Column(name = "exception")
    private String exception;
}

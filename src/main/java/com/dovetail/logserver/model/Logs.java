package com.dovetail.logserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_log")
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "eventDate")
    private LocalDate eventDate;

    @Column(name = "logger")
    private String logger;

    @Column(name = "level")
    private String level;

    @Column(name = "message")
    private String message;

    @Column(name = "exception")
    private String exception;
}

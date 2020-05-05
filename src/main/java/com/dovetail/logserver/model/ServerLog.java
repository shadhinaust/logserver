package com.dovetail.logserver.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "server_logs")
public class ServerLog extends Auditable<String> {

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

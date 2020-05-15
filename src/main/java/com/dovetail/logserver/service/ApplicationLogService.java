package com.dovetail.logserver.service;

import com.dovetail.logserver.model.ApplicationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ApplicationLogService {

    Page<ApplicationLog> findAllApplicationLogsByType(String type, Pageable pageable);

    Page<ApplicationLog> findAllApplicationLogsByTypeAndDateTimeRange(String type, LocalDateTime from, LocalDateTime to, Pageable pageable);
}

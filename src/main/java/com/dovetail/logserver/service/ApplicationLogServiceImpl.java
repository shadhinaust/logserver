package com.dovetail.logserver.service;

import com.dovetail.logserver.model.ApplicationLog;
import com.dovetail.logserver.repository.ApplicationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplicationLogServiceImpl implements ApplicationLogService {

    @Autowired
    private ApplicationLogRepository applicationLogRepository;

    @Override
    public Page<ApplicationLog> findAllApplicationLogsByType(String type, Pageable pageable) {
        return applicationLogRepository.findAllByTypeOrderByDateTimeDesc(type, pageable);
    }

    @Override
    public Page<ApplicationLog> findAllApplicationLogsByTypeAndDateTimeRange(String type, LocalDateTime from, LocalDateTime to, Pageable pageable) {
        return applicationLogRepository.findAllByTypeAndDateTimeBetweenOrderByDateTimeDesc(type, from, to, pageable);
    }

}

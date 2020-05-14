package com.dovetail.logserver.service;

import com.dovetail.logserver.model.ApplicationLog;
import com.dovetail.logserver.repository.ApplicationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationLogServiceImpl implements ApplicationLogService {

    @Autowired
    private ApplicationLogRepository applicationLogRepository;

    @Override
    public List<ApplicationLog> findAllApplicationLogs() {
        return applicationLogRepository.findAll();
    }
}

package com.dovetail.logserver.service;

import com.dovetail.logserver.model.ApplicationLog;
import com.dovetail.logserver.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsServiceImpl implements LogsService {

    @Autowired
    private LogsRepository logsRepository;

    @Override
    public List<ApplicationLog> findAllLogs() {
        return logsRepository.findAll();
    }
}

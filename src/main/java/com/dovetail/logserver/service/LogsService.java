package com.dovetail.logserver.service;

import com.dovetail.logserver.model.Logs;

import java.util.List;

public interface LogsService {
    List<Logs> findAllLogs();
}

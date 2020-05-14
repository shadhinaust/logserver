package com.dovetail.logserver.service;

import com.dovetail.logserver.model.ApplicationLog;

import java.util.List;

public interface ApplicationLogService {
    List<ApplicationLog> findAllApplicationLogs();
}

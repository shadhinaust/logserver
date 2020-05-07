package com.dovetail.logserver.service;

import com.dovetail.logserver.model.ServerLog;
import com.dovetail.logserver.repository.ServerLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServerLogServiceImpl implements ServerLogService {

    @Autowired
    ServerLogRepository serverLogRepository;

    @Override
    public String findGreeting() {
        return "Hello service from Log Server";
    }

    @Override
    public ServerLog saveServerLog(ServerLog serverLog) {
        return serverLogRepository.save(serverLog);
    }

    @Override
    public List<ServerLog> findAllServerLogs() {
        return serverLogRepository.findAll();
    }

    @Override
    public String getError(String errorCode) {
        if(errorCode == null) {
            throw new NullPointerException("Nothing Found!");
        }
        return errorCode;
    }
}

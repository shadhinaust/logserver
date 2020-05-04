package com.dovetail.logserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dovetail.logserver.model.ServerLog;
import com.dovetail.logserver.repository.ServerLogRepository;

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
}

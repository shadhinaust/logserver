package com.dovetail.logserver.service;

import java.util.List;

import com.dovetail.logserver.model.ServerLog;

public interface ServerLogService {
	
	String findGreeting();
	
	ServerLog saveServerLog(ServerLog serverLog);
	
	List<ServerLog> findAllServerLogs();
}

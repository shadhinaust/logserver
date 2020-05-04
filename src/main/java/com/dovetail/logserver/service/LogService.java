package com.dovetail.logserver.service;

import java.util.List;

import com.dovetail.logserver.model.Log;

public interface LogService {
	
	String findGreeting();
	
	Log saveLog(Log log);
	
	List<Log> findAllLogs();
}

package com.dovetail.logserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dovetail.logserver.model.Log;
import com.dovetail.logserver.repository.LogRepository;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	LogRepository logRepository;
	
	@Override
	public String findGreeting() {
		// TODO Auto-generated method stub
		return "Hello service from Log Server";
	}

	@Override
	public Log saveLog(Log log) {
		return logRepository.save(log);
	}

	@Override
	public List<Log> findAllLogs() {
		return logRepository.findAll();
	}
}

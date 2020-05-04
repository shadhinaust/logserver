package com.dovetail.logserver.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dovetail.logserver.model.Log;
import com.dovetail.logserver.service.LogService;

@RestController()
public class LogController {
	
	@Autowired
	LogService logService;
	
	@GetMapping("/greetings")
	public String getGreeting() {
		return logService.findGreeting();
	}
	
	@GetMapping("/logs")
	public List<Log> getLogs() {
		return logService.findAllLogs();
	}
	
	@PostMapping("/post-logs")
	public Boolean postLogs() {
		int index = 0;
		Log log;
		while(index++ < 10) {
			log = new Log();
			log.setDateTime(LocalDateTime.now());
			log.setMessage(index + ": Severe thunderstorms are possible from Oklahoma and eastern portions of Kansas");
			log.setFormat(".mp3");
			log.setDuration(5000L);
			logService.saveLog(log);
		}
		
		return true;
	}

}
;
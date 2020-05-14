package com.dovetail.logserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dovetail.logserver.advice.Logger;
import com.dovetail.logserver.converter.ApplicationLogDataConverter;
import com.dovetail.logserver.dto.ApplicationLogDto;
import com.dovetail.logserver.dto.UserDto;
import com.dovetail.logserver.model.LogLevel;
import com.dovetail.logserver.service.ApplicationLogService;
import com.dovetail.logserver.service.ServerLogService;
import com.dovetail.logserver.service.UserService;
import com.dovetail.logserver.utils.JwtUtils;

@RestController()
public class ServerLogController {

	@Autowired
	private ServerLogService serverLogService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserService userService;
	@Autowired
	private ApplicationLogService logsService;

	@Logger(message = "Authenticating user informations.")
	@PostMapping({ "/", "/auth" })
	public ResponseEntity authenticate(@RequestBody UserDto userData) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userData.getUsername(), userData.getPassword()));
		} catch (Exception ex) {
			return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
		}
		final UserDetails userDetails = userService.loadUserByUsername(userData.getUsername());
		final String token = jwtUtils.generateToken(userDetails);

		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	@Logger(message = "Greetings from the team.")
	@GetMapping("/greetings")
	public ResponseEntity getGreeting() {
		return new ResponseEntity<>(serverLogService.findGreeting(), HttpStatus.OK);
	}

	@Logger(message = "Fetching all logs")
	@GetMapping("/logs")
	public ResponseEntity getLogs() {
		List<ApplicationLogDto> logs = new ArrayList<>();
		logsService.findAllApplicationLogs().forEach(log -> logs.add(ApplicationLogDataConverter.convertApplicationLog(log)));
		return new ResponseEntity<>(logs, HttpStatus.OK);
	}

	@Logger(level = LogLevel.FATAL, message = "Error generator")
	@PostMapping("/error-log")
	public ResponseEntity error() {
		try {
			serverLogService.getError(null);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
	}
}
package com.dovetail.logserver.controller;

import com.dovetail.logserver.advice.Logger;
import com.dovetail.logserver.dto.ApplicationLogDto;
import com.dovetail.logserver.dto.ResponseDto;
import com.dovetail.logserver.dto.UserDto;
import com.dovetail.logserver.exception.NotFoundException;
import com.dovetail.logserver.model.LogLevel;
import com.dovetail.logserver.service.UserService;
import com.dovetail.logserver.service.facade.ApplicationLogServiceFacade;
import com.dovetail.logserver.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ServerLogController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationLogServiceFacade applicationLogServiceFacade;

    @Logger(message = "Authenticating user informations.")
    @PostMapping({"/", "/auth"})
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
        return new ResponseEntity<>("Hello from Team!", HttpStatus.OK);
    }

    @Logger(message = "Fetching all logs")
    @GetMapping("/logs")
    public ResponseEntity getLogs(@RequestParam(name = "type", required = true, defaultValue = "EVENT") String type,
                                  @RequestParam(name = "filter", required = false) String filter,
                                  @RequestParam(name = "from", required = false) String from,
                                  @RequestParam(name = "to", required = false) String to,
                                  @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNo < 2 ? 0 : pageNo - 1, pageSize);
        ResponseDto logs = applicationLogServiceFacade.getAllApplicationLogs(type, filter, from, to, pageable);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @Logger(level = LogLevel.FATAL, message = "Error generator")
    @PostMapping("/error-log")
    public ResponseEntity error() {
        try {
            throw new NotFoundException();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
}
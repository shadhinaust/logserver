package com.dovetail.logserver.controller;

import com.dovetail.logserver.dto.ServerLogDto;
import com.dovetail.logserver.dto.UserDto;
import com.dovetail.logserver.model.ServerLog;
import com.dovetail.logserver.service.ServerLogService;
import com.dovetail.logserver.service.UserService;
import com.dovetail.logserver.utils.JwtUtils;
import com.dovetail.logserver.utils.ServerLogUtils;
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

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping({"/", "/auth"})
    public ResponseEntity authenticate(@RequestBody UserDto userData) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userData.getUsername(), userData.getPassword()));
        } catch (Exception ex) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = userService.loadUserByUsername(userData.getUsername());
        final String token = jwtUtils.generateToken(userDetails);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/greetings")
    public ResponseEntity getGreeting() {
        return new ResponseEntity<>(serverLogService.findGreeting(), HttpStatus.OK);
    }

    @GetMapping("/logs")
    public ResponseEntity getLogs() {
        List<ServerLogDto> serverLogs = new ArrayList<>();
        serverLogService.findAllServerLogs().forEach(serverLog ->
                serverLogs.add(ServerLogDto.builder()
                        .id(serverLog.getId().toString())
                        .username(serverLog.getCreatedBy())
                        .dateTime(ServerLogUtils.toDateTimeString(serverLog.getDateTime()))
                        .message(serverLog.getMessage())
                        .format(serverLog.getFormat())
                        .duration(serverLog.getDuration())
                        .createdAt(ServerLogUtils.toDateTimeString(serverLog.getCreatedAt()))
                        .build())
        );

        return new ResponseEntity<>(serverLogs, HttpStatus.OK);
    }

    @PostMapping("/save-log")
    public ResponseEntity saveLog(@RequestBody ServerLogDto serverLogData) {
        ServerLog serverLog = ServerLog.builder()
                .message(serverLogData.getMessage())
                .format(serverLogData.getFormat())
                .duration(serverLogData.getDuration())
                .build();

        ServerLogDto serverLogResponse;
        try {
            serverLog = serverLogService.saveServerLog(serverLog);
            serverLogResponse = ServerLogDto.builder()
                    .id(serverLog.getId().toString())
                    .username(serverLog.getCreatedBy())
                    .dateTime(ServerLogUtils.toDateTimeString(serverLog.getDateTime()))
                    .message(serverLog.getMessage())
                    .format(serverLog.getFormat())
                    .duration(serverLog.getDuration())
                    .createdAt(ServerLogUtils.toDateTimeString(serverLog.getCreatedAt()))
                    .build();
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(serverLogResponse, HttpStatus.OK);
    }

    @PostMapping("/init-logs")
    public ResponseEntity initLogs() {
        List<ServerLogDto> serverLogs = new ArrayList<>();

        ServerLog serverLog;
        int index = 0;
        while (index++ < 10) {
            try {
                serverLog = ServerLog.builder()
                        .message(index + ": Severe thunderstorms are possible from Oklahoma and eastern portions of Kansas")
                        .format(".mp3")
                        .duration(5000L)
                        .build();
                serverLog = serverLogService.saveServerLog(serverLog);

                serverLogs.add(ServerLogDto.builder()
                        .id(serverLog.getId().toString())
                        .username(serverLog.getCreatedBy())
                        .dateTime(ServerLogUtils.toDateTimeString(serverLog.getDateTime()))
                        .message(serverLog.getMessage())
                        .format(serverLog.getFormat())
                        .duration(serverLog.getDuration())
                        .createdAt(ServerLogUtils.toDateTimeString(serverLog.getCreatedAt()))
                        .build());
            } catch (Exception ex) {
            }
        }

        return new ResponseEntity<>(serverLogs, HttpStatus.OK);
    }
}
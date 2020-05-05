package com.dovetail.logserver.controllers;

import com.dovetail.logserver.dtos.ServerLogDto;
import com.dovetail.logserver.dtos.UserDto;
import com.dovetail.logserver.models.ServerLog;
import com.dovetail.logserver.services.ServerLogService;
import com.dovetail.logserver.services.UserService;
import com.dovetail.logserver.utils.JwtUtils;
import com.dovetail.logserver.utils.ServerLogUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Log4j2
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
    public String authenticate(@RequestBody UserDto userData) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userData.getUsername(), userData.getPassword()));
        } catch (Exception ex) {
            log.error("Incorrect username or password", ex);
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = userService.loadUserByUsername(userData.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);

        return jwt;
    }

    @GetMapping("/greetings")
    public String getGreeting() {
        return serverLogService.findGreeting();
    }

    @GetMapping("/logs")
    public List<ServerLogDto> getLogs() {
        List<ServerLogDto> serverLogs = new ArrayList<>();
        serverLogService.findAllServerLogs().forEach(serverLog ->
                serverLogs.add(ServerLogDto.builder()
                        .id(serverLog.getId().toString())
                        .dateTime(ServerLogUtils.toDateTimeString(serverLog.getDateTime()))
                        .message(serverLog.getMessage())
                        .format(serverLog.getFormat())
                        .duration(serverLog.getDuration())
                        .build())
        );

        return serverLogs;
    }

    @PostMapping("/save-log")
    public ServerLogDto saveLog(@RequestBody ServerLogDto serverLogData) {
        ServerLog serverLog = ServerLog.builder()
                .message(serverLogData.getMessage())
                .format(serverLogData.getFormat())
                .duration(serverLogData.getDuration())
                .build();

        ServerLogDto serverLogResponse;
        try {
            serverLogService.saveServerLog(serverLog);
            serverLogResponse = ServerLogDto.builder()
                    .id(serverLog.getId().toString())
                    .dateTime(ServerLogUtils.toDateTimeString(serverLog.getDateTime()))
                    .message(serverLog.getMessage())
                    .format(serverLog.getFormat())
                    .duration(serverLog.getDuration())
                    .build();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ServerLogDto.builder().build();
        }

        return serverLogResponse;
    }

    @PostMapping("/init-logs")
    public List<ServerLogDto> initLogs() {
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
                        .dateTime(ServerLogUtils.toDateTimeString(serverLog.getDateTime()))
                        .message(serverLog.getMessage())
                        .format(serverLog.getFormat())
                        .duration(serverLog.getDuration())
                        .build());
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }

        return serverLogs;
    }
}
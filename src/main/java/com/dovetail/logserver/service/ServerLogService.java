package com.dovetail.logserver.service;

import com.dovetail.logserver.model.ServerLog;

import java.util.List;

public interface ServerLogService {

    String findGreeting();

    ServerLog saveServerLog(ServerLog serverLog);

    List<ServerLog> findAllServerLogs();
}

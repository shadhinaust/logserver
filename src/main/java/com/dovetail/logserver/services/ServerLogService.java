package com.dovetail.logserver.services;

import com.dovetail.logserver.models.ServerLog;

import java.util.List;

public interface ServerLogService {

    String findGreeting();

    ServerLog saveServerLog(ServerLog serverLog);

    List<ServerLog> findAllServerLogs();
}

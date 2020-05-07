package com.dovetail.logserver.service;

import com.dovetail.logserver.model.ServerLog;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface ServerLogService {

    String findGreeting();

    ServerLog saveServerLog(ServerLog serverLog);

    List<ServerLog> findAllServerLogs();

    String getError(String errorCode);
}

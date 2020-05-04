package com.dovetail.logserver.repository;

import com.dovetail.logserver.model.ServerLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerLogRepository extends JpaRepository<ServerLog, Long> {

}

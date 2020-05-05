package com.dovetail.logserver.repositories;

import com.dovetail.logserver.models.ServerLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerLogRepository extends JpaRepository<ServerLog, Long> {

}

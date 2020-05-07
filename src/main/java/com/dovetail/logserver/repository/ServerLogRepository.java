package com.dovetail.logserver.repository;

import com.dovetail.logserver.model.ServerLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServerLogRepository extends JpaRepository<ServerLog, Long> {
    Optional<ServerLog> findById(Long id);
}

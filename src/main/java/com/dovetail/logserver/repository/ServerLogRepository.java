package com.dovetail.logserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dovetail.logserver.model.ServerLog;

public interface ServerLogRepository extends JpaRepository<ServerLog, Long>{

}

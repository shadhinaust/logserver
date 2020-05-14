package com.dovetail.logserver.repository;

import com.dovetail.logserver.model.ApplicationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Long> {
}

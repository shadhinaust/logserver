package com.dovetail.logserver.repository;

import com.dovetail.logserver.model.ApplicationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Long> {
    Page<ApplicationLog> findAllByTypeOrderByDateTimeDesc(String type, Pageable pageable);

    Page<ApplicationLog> findAllByTypeAndDateTimeBetweenOrderByDateTimeDesc(String type, LocalDateTime from, LocalDateTime to, Pageable pageable);
}

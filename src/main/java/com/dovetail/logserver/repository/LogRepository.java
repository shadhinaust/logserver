package com.dovetail.logserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dovetail.logserver.model.Log;

public interface LogRepository extends JpaRepository<Log, Long>{

}

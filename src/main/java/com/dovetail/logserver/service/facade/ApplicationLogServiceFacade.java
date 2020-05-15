package com.dovetail.logserver.service.facade;

import com.dovetail.logserver.dto.ResponseDto;
import org.springframework.data.domain.Pageable;

public interface ApplicationLogServiceFacade {
    ResponseDto getAllApplicationLogs(String type, String filter, String fromDateTime, String toDateTime, Pageable pageable);
}

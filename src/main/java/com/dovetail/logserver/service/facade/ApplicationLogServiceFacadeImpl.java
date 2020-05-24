package com.dovetail.logserver.service.facade;

import com.dovetail.logserver.converter.ApplicationLogDataConverter;
import com.dovetail.logserver.dto.ApplicationLogDto;
import com.dovetail.logserver.dto.ResponseDto;
import com.dovetail.logserver.model.ApplicationLog;
import com.dovetail.logserver.model.LogFilter;
import com.dovetail.logserver.service.ApplicationLogService;
import com.dovetail.logserver.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationLogServiceFacadeImpl implements ApplicationLogServiceFacade {

    @Autowired
    private ApplicationLogService applicationLogService;

    @Override
    public ResponseDto getAllApplicationLogs(String type, String filter, String fromDateTime, String toDateTime, Pageable pageable) {

        LocalDateTime from = null;
        LocalDateTime to = null;

        if (!StringUtils.isEmpty(fromDateTime) && !StringUtils.isEmpty(toDateTime)) {
            from = DateTimeUtils.toDateTime(fromDateTime);
            to = DateTimeUtils.toDateTime(toDateTime);
        } else if (!StringUtils.isEmpty(filter)) {
            LogFilter logFilter = LogFilter.getByKey(filter);
            to = LocalDateTime.now();
            switch (logFilter) {
                case RECENT:
                case LAST_12_HOURS:
                case LAST_24_HOURS:
                    from = to.minusHours(logFilter.getValue());
                    break;
                case LAST_3_DAYS:
                case LAST_7_DAYS:
                case LAST_15_DAYS:
                    from = to.minusDays(logFilter.getValue());
                    break;
                case LAST_1_MONTH:
                    from = to.minusMonths(logFilter.getValue());
                    break;
                case ALL_TIME:
                default:
                    from = to = null;
                    break;
            }
        }

        Page<ApplicationLog> logPage;
        if (from != null) {
            logPage = applicationLogService.findAllApplicationLogsByTypeAndDateTimeRange(type, from, to, pageable);
        } else {
            logPage = applicationLogService.findAllApplicationLogsByType(type, pageable);
        }
        List<ApplicationLogDto> logs = new ArrayList<>();
        logPage.forEach(log -> logs.add(ApplicationLogDataConverter.convertApplicationLog(log)));

        return ResponseDto.builder().
                data(logs)
                .hasNext(logPage.hasNext())
                .totalPages(logPage.getTotalPages())
                .build();
    }
}

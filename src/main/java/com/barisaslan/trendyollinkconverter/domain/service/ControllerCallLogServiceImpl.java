package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.dao.entity.ControllerCallLog;
import com.barisaslan.trendyollinkconverter.dao.repository.ControllerCallLogRepository;
import com.barisaslan.trendyollinkconverter.domain.model.ControllerCallLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ControllerCallLogServiceImpl implements ControllerCallLogService {

    private final ControllerCallLogRepository controllerCallLogRepository;

    @Override
    public ControllerCallLog create(ControllerCallLogDto dto) {
        ControllerCallLog entity = new ControllerCallLog();
        entity.setRequestBody(dto.getRequestBody());
        entity.setResponseBody(dto.getResponseBody());
        entity.setApiPath(dto.getApiPath());
        entity.setExecutionTime(dto.getEndTime() - dto.getStartTime());
        entity.setStatusCode(dto.getStatusCode());
        entity.setInsertDateTime(new Date());

        return controllerCallLogRepository.save(entity);
    }

}

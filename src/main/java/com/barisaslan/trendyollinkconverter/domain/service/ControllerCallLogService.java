package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.dao.entity.ControllerCallLog;
import com.barisaslan.trendyollinkconverter.domain.model.ControllerCallLogDto;

public interface ControllerCallLogService {

    ControllerCallLog create(ControllerCallLogDto dto);

}

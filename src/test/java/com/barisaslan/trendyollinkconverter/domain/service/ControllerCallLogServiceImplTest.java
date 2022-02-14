package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.dao.entity.ControllerCallLog;
import com.barisaslan.trendyollinkconverter.dao.repository.ControllerCallLogRepository;
import com.barisaslan.trendyollinkconverter.domain.model.ControllerCallLogDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.barisaslan.trendyollinkconverter.TestHelper.getFakeControllerCallLogDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControllerCallLogServiceImplTest {

    @InjectMocks
    private ControllerCallLogServiceImpl controllerCallLogService;

    @Mock
    private ControllerCallLogRepository controllerCallLogRepository;

    @Test
    void createShouldSaveEntity() {
        when(controllerCallLogRepository.save(any())).thenReturn(any(ControllerCallLogDto.class));

        controllerCallLogService.create(getFakeControllerCallLogDto());

        verify(controllerCallLogRepository).save(any(ControllerCallLog.class));
    }

}
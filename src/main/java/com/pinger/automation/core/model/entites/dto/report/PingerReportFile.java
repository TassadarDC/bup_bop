package com.pinger.automation.core.model.entites.dto.report;

import com.pinger.automation.core.model.entites.dto.PingerDataDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PingerReportFile extends PingerDataDto {
    private ReportDto dto;
}

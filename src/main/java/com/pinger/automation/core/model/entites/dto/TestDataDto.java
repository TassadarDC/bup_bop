package com.pinger.automation.core.model.entites.dto;

import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import com.pinger.automation.core.model.entites.dto.report.ReportDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestDataDto {
    private FileData<ConfigDto> config = new FileData<>();
    private FileData<ReportDto> report = new FileData<>();
}

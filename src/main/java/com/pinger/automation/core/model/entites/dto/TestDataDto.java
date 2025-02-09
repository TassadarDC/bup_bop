package com.pinger.automation.core.model.entites.dto;

import com.pinger.automation.core.model.entites.dto.config.PingerConfigFile;
import com.pinger.automation.core.model.entites.dto.report.PingerReportFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TestDataDto {
    private PingerConfigFile config = new PingerConfigFile();
    private PingerReportFile report = new PingerReportFile();
}

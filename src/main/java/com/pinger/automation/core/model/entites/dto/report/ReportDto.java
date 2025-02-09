package com.pinger.automation.core.model.entites.dto.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ReportDto {
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;
    @JsonProperty("min_successful_pings")
    private int minSuccessfulPings;
    @JsonProperty("max_pings")
    private int maxPings;
    private List<EntryDto> entries;
}

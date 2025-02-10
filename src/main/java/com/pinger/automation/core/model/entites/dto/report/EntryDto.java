package com.pinger.automation.core.model.entites.dto.
        report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import lombok.Data;

@Data
public class EntryDto {
    private EndpointDto endpoint;
    @JsonProperty("total_pings")
    private int totalPings;
    @JsonProperty("successful_pings")
    private int successfulPings;
}

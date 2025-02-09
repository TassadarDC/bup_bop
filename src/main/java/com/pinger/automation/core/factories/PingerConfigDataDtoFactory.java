package com.pinger.automation.core.factories;

import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import com.pinger.automation.core.model.enums.Endpoint;

import java.util.Collections;

public class PingerConfigDataDtoFactory {
    public ConfigDto getPingDataDto(Endpoint endpoint) {
        return getPingDataDto(endpoint, 1, 1);
    }

    public ConfigDto getPingDataDto(Endpoint endpoint, int minSuccess, int maxPings) {
        return getPingDataDto(endpoint, minSuccess, maxPings, endpoint.isIgnore());
    }

    public ConfigDto getPingDataDto(Endpoint endpoint, int minSuccess, int maxPings, boolean ignore) {
        EndpointDto endpointDto = new EndpointDto(endpoint.getAddress(), endpoint.getDescription(), ignore);
        ConfigDto config = new ConfigDto();

        config.setMinSuccessfulPings(minSuccess);
        config.setMaxPings(maxPings);
        config.setEndpoints(Collections.singletonList(endpointDto));
        return config;
    }
}

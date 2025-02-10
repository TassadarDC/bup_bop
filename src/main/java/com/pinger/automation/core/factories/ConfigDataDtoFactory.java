package com.pinger.automation.core.factories;

import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import com.pinger.automation.core.model.enums.Endpoint;

import java.util.Collections;

public final class ConfigDataDtoFactory {
    public static ConfigDto getConfigDataDto(Endpoint endpoint) {
        return getConfigDataDto(endpoint, 1, 1);
    }

    public static ConfigDto getConfigDataDto(Endpoint endpoint, int minSuccess, int maxPings) {
        return getConfigDataDto(endpoint, minSuccess, maxPings, endpoint.isIgnore());
    }

    public static ConfigDto getConfigDataDto(Endpoint endpoint, int minSuccess, int maxPings, boolean ignore) {
        EndpointDto endpointDto = new EndpointDto(endpoint.getAddress(), endpoint.getDescription(), ignore);
        ConfigDto config = new ConfigDto();

        config.setMinSuccessfulPings(minSuccess);
        config.setMaxPings(maxPings);
        config.setEndpoints(Collections.singletonList(endpointDto));
        return config;
    }
}

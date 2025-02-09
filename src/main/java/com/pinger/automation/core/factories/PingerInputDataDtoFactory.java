package com.pinger.automation.core.factories;

import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import com.pinger.automation.core.model.enums.Endpoint;

import java.util.Collections;

public class PingerInputDataDtoFactory {
    public InputDataDto getPingDataDto() {
        return getPingDataDto(Endpoint.CLOUDFLARE_DNS, 1, 1);
    }

    public InputDataDto getPingDataDto(Endpoint endpoint) {
        return getPingDataDto(endpoint, 1, 1);
    }

    public InputDataDto getPingDataDto(Endpoint endpoint, int minSuccess, int maxPings) {
        return getPingDataDto(endpoint, minSuccess, maxPings, endpoint.isIgnore());
    }

    public InputDataDto getPingDataDto(Endpoint endpoint, int minSuccess, int maxPings, boolean ignore) {
        EndpointDto endpointDto = new EndpointDto(endpoint.getAddress(), endpoint.getDescription(), ignore);
        InputDataDto config = new InputDataDto();

        config.setMinSuccessfulPings(minSuccess);
        config.setMaxPings(maxPings);
        config.setEndpoints(Collections.singletonList(endpointDto));
        return config;
    }
}

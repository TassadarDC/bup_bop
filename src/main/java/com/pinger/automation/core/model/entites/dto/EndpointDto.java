package com.pinger.automation.core.model.entites.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinger.automation.core.model.enums.Endpoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EndpointDto {
    @JsonProperty("addr")
    private String address;
    @JsonProperty("description")
    private String description;
    @JsonProperty("ignore")
    private boolean ignore;

    public EndpointDto(Endpoint endpoint) {
        this.address = endpoint.getAddress();
        this.description = endpoint.getDescription();
        this.ignore = endpoint.isIgnore();
    }
}


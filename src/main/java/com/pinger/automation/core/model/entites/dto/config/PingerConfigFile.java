package com.pinger.automation.core.model.entites.dto.config;

import com.pinger.automation.core.model.entites.dto.PingerDataDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PingerConfigFile extends PingerDataDto {
    private ConfigDto dto;
}

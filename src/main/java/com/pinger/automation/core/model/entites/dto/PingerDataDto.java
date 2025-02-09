package com.pinger.automation.core.model.entites.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PingerDataDto {
    private String name;
    private String directory;
    private String path;

    public String getPath() {
        return directory.concat(name);
    }
}

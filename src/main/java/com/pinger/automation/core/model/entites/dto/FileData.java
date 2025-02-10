package com.pinger.automation.core.model.entites.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileData<T> {
    private String name;
    private String directory;
    private String path;
    private T dto;

    public String getPath() {
        return directory.concat(name);
    }
}

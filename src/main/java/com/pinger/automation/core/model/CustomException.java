package com.pinger.automation.core.model;

import java.io.IOException;

public class CustomException extends RuntimeException {
    public CustomException(String message, Throwable reason) {
        super(message, reason);
    }

    public CustomException(String message, IOException reason) {
        super(message, reason);
    }
}

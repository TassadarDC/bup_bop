package com.pinger.automation.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public final class PingerAppConfig {
    private static final Config CONFIG = ConfigFactory.load();
    public static final String PINGER_WORKING_DIRECTORY = "pinger.workingDirectory";
    public static final String PINGER_EXECUTABLE = "pinger.executable";
    public static final String PINGER_PINGER_SCHEMA = "pinger.pingerSchema";

    public static String getPingerWorkingDirectory() {
        return getString(PINGER_WORKING_DIRECTORY);
    }
    public static String getPingerExecutable() {
        return getString(PINGER_EXECUTABLE);
    }
    public static String getPingerJsonSchema() {
        return getString(PINGER_PINGER_SCHEMA);
    }
    private static String getString(String key) {
        return CONFIG.hasPath(key) ? CONFIG.getString(key) : "";
    }
}

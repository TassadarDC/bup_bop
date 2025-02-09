package com.pinger.automation.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class PingerConfig {
    private static final Config config = ConfigFactory.load();

    private static String getString(String key) {
        return config.hasPath(key) ? config.getString(key) : "";
    }

    public static String getPingerWorkingDirectory() {
        return getString("pinger.workingDirectory");
    }

    public static String getPingerExecutable() {
        return getString("pinger.executable");
    }
    public static String getPingerJsonSchema() {
        return getString("pinger.pingerSchema");
    }
}

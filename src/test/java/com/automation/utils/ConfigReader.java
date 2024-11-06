package com.automation.utils;

import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static void initConfig() {
        try {
            prop = new Properties();
            prop.load(new FileReader("src/test/resources/config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        prop.setProperty(key, value);
    }

    public static Object getObject(String key) {
        return prop.get(key);
    }

    public static void setObject(String key, Object value) {
        prop.putIfAbsent(key, value);
    }


}

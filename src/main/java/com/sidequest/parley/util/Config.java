package com.sidequest.parley.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

@SuppressWarnings("unused")
public class Config {
    private static Properties properties;

    static {
        properties = new Properties();
        System.setProperty("user.dir", "C:\\Users\\jrack\\OneDrive\\Documents\\parley");
        String configFile = System.getenv("PARLEY_CONFIG_FILE");
        
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println("configFile: " + configFile);
        if (configFile == null) {
            throw new IllegalStateException("Environmental variable PARLEY_CONFIG_FILE is not set");
        }
        
      //  try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(configFile)) {
        try (InputStream inputStream = new FileInputStream(configFile)){
            if (inputStream == null) {
                throw new IOException("Config file not found: " + configFile);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

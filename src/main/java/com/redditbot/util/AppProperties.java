package com.redditbot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
    private static final Logger logger = LoggerFactory.getLogger(AppProperties.class);

    private static Properties prop;

    static {
        try {
            if(prop == null) {
                prop = new Properties();
                InputStream inputStream = AppProperties.class.getClassLoader().getResourceAsStream("application.properties");
                prop.load(inputStream);
            }
        } catch (Exception e) {
            logger.error("Exception loading application properties: ", e);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

}

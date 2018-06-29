package com.lipenglong.passport.cas;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * properties文件工具类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class PropertiesUtil {
    public static String getValue(String fileName, String key) {
        if (!StringUtils.hasText(fileName) || !StringUtils.hasText(key)) {
            return "";
        }
        InputStreamReader reader = null;
        Properties properties = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            reader = new InputStreamReader(classLoader.getResourceAsStream(fileName), "utf-8");
            properties.load(reader);
            return properties.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

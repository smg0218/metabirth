package com.metabirth.util;

import com.metabirth.config.JDBCConnection;

import java.util.Properties;

public class PropertiesUtil {
    public static final Properties properties = new Properties();

    /**
     * config.properties에서 key 값의 내용을 꺼내오는 메서드
     * @param key 꺼낼 값의 이름(key)
     * @return key를 바탕으로 꺼내진 값(value)
     */
    public static String getProperty(String key) {
        try {
            properties.load(JDBCConnection.class.getClassLoader().getResourceAsStream("config.properties"));
            return properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

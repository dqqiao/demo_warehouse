package com.nov.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author HONY
 * @date 2018/11/10 16:08
 */
public class PropertyUtil {

    private static String filePath = "demo.properties";
    private static Properties props;
    // 读取配置
    static {
        props = new Properties();
        InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(filePath);
        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String key){
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperties(String key, String defaultValue){
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
            return defaultValue;
        }
        return value.trim();
    }



}

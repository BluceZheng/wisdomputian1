package com.haomeng.wisdomputian.util;

import java.util.Properties;

public class PropertiesUtil {
    public static Properties getProperties(String fileName) {
        Properties prop = new Properties();
        try {
            prop.load(PropertiesUtil.class.getResourceAsStream("/" + fileName));
        } catch (Exception e) {
            return null;
        }
        return prop;
    }
}

package com.gmail.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    public static Properties loadProps(String filePath){
        Properties props =  new Properties();
        try {
            props.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}

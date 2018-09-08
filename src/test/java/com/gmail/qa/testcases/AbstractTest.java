package com.gmail.qa.testcases;

import com.gmail.qa.bo.Account;
import com.gmail.qa.utils.PropertyLoader;
import org.testng.annotations.BeforeClass;

import java.util.Properties;

public class AbstractTest {
    public Properties prop;
    public Account account;

    @BeforeClass
    public void setUpConfigFileProperties(){
        prop = PropertyLoader.loadProps("src/main/java/com/gmail/qa/config/config.properties");
        account = new Account(prop.getProperty("username"), prop.getProperty("password"));
    }
}

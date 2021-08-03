package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private String driver;
    private String url;
    private String user;
    private String password;
    private String dialect;

    public AppProperties(String fileName) {
        setAppPropertiesValue(getProperties(fileName));
    }

    private void setAppPropertiesValue(Properties properties){
        this.driver = properties.getProperty("driver");
        this.url = properties.getProperty("url");
        this.user = properties.getProperty("user");
        this.password = properties.getProperty("password");
        this.dialect = properties.getProperty("dialect");
    }

    private Properties getProperties(String fileName) {
        Properties properties = new Properties();

        InputStream inputStream = getClass().getResourceAsStream(fileName);
        try {
            properties.load(inputStream);
            return properties;
        } catch (IOException exception) {
            return new Properties();
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
}

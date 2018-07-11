package pl.unity.mule.gatx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class Configuration {

    private static final String CONFIG_PROPERTIES = "test-config.properties";
    private static final String KEY_PROPS_WEBSERVICE_AUTHORIZATION = "webservice.authorization";
    private static final String KEY_PROPS_WEBSERVICE_URL = "webservice.url";
    private static final String KEY_PROPS_JDBC_DRIVER = "jdbc.driver";
    private static final String KEY_PROPS_JDBC_URL = "jdbc.url";
    private static final String KEY_PROPS_JDBC_USERNAME = "jdbc.username";
    private static final String KEY_PROPS_JDBC_PASSWORD = "jdbc.password";

    private final Properties properties = new Properties();
    private final static Configuration INSTANCE = new Configuration();

    private Configuration(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(CONFIG_PROPERTIES)).getFile());
        try{
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration getInstance(){
        return INSTANCE;
    }

    public String getWebserviceURL(){
        return properties.getProperty(KEY_PROPS_WEBSERVICE_URL);
    }
    public String getWebserviceAuthorization() {
        return properties.getProperty(KEY_PROPS_WEBSERVICE_AUTHORIZATION);
    }
    public String getJdbcDriver(){
    	return properties.getProperty(KEY_PROPS_JDBC_DRIVER);
    }
    public String getJdbcUrl(){
    	return properties.getProperty(KEY_PROPS_JDBC_URL);
    }
    public String getJdbcUserName(){
    	return properties.getProperty(KEY_PROPS_JDBC_USERNAME);
    }

    public String getJdbcPassword(){
    	return properties.getProperty(KEY_PROPS_JDBC_PASSWORD);
    }
}


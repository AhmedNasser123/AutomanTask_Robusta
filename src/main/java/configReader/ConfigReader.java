package configReader;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import utils.LoggerUtil;

public class ConfigReader {
    public static final Configuration config;
    private static final LoggerUtil logger = new LoggerUtil(ConfigReader.class);

    static {
        try {
            Configurations configs = new Configurations();
            config = configs.properties("src/main/resources/config.properties");
        } catch (Exception e) {
            logger.error("Error loading configuration", e);
            throw new RuntimeException("Could not load configuration file", e);
        }
    }

    public String getProperty(String key, String defaultValue) {
        return config.getString(key, defaultValue);
    }

    public int getIntProperty(String key, int defaultValue) {
        return config.getInt(key, defaultValue);
    }

}
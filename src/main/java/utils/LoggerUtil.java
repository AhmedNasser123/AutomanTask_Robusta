package utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private final Logger logger;

    public LoggerUtil(Class<?> clazz) {
        this.logger = LogManager.getLogger(clazz);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message, String name) {
        logger.warn(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void trace(String message) {
        logger.trace(message);
    }

    public void log(Level level, String message) {
        logger.log(level, message);
    }
}

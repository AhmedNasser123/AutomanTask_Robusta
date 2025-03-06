package listener;

import configReader.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.LoggerUtil;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final LoggerUtil logger = new LoggerUtil(RetryAnalyzer.class);
    private int retryCount = 0;
    private static final ConfigReader config = new ConfigReader();
    private final int maxRetryCount = config.getIntProperty("retry.count", 0);

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            logger.error("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + retryCount + " time(s)");
            return true;
        }
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }
}
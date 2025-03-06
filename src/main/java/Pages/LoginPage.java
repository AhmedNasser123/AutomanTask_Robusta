package Pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import utils.LoggerUtil;

import java.text.MessageFormat;


public class LoginPage extends BasePage {

    private final LoggerUtil logger;
    public LoginPage(Page page) {
        super(page);
        logger = new LoggerUtil(LoginPage.class);
    }
    private static final String TXT_UserName = "#user-name";
    private static final String TXT_Password = "#password";
    private static final String BTN_Login = "#login-button";

    public void navigateToLoginPage(String URL) {
        navigate(URL);
        logger.info("Navigated to login page: " + URL);
    }

      public String SubmitLoginByStandardUser(String UserName , String Password) {
       fill(TXT_UserName,UserName);
       fill(TXT_Password,Password);
       click(BTN_Login);
       logger.info(MessageFormat.format("Current URL : {0}", getCurrentUrl(page)));
       return getCurrentUrl(page);
    }
}

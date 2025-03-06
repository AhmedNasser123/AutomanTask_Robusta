package base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import utils.LoggerUtil;

public abstract class BasePage {
    protected Page page;
    protected final LoggerUtil logger;
    public BasePage(Page page) {
        this.page = page;
        this.logger = new LoggerUtil(this.getClass());
    }
    public void navigate(String url) {
        page.navigate(url);
    }
    public static String getCurrentUrl(Page page) {
        return page.url();
    }


    public boolean isDisplayed(String selector) {
        try {
            return page.locator(selector).isVisible();
        } catch (Exception e) {
            return false;
        }
    }


    public void click(String selector) {
        try {
            page.waitForLoadState(LoadState.NETWORKIDLE);
            page.click(selector);
        } catch (Exception e) {
            logger.error("Failed to click element: " + selector, e);
            throw new RuntimeException("Click failed: " + selector, e);
        }
    }

    public void fill(String selector, String text) {
        try {
            page.locator(selector).clear();
            page.fill(selector, text);
        } catch (Exception e) {
            logger.error("Failed to type text: " + selector, e);
            throw new RuntimeException("Type failed: " + selector, e);
        }
    }

    public String getText(String selector) {
        try {
            page.waitForLoadState(LoadState.NETWORKIDLE);
            return page.textContent(selector);
        } catch (Exception e) {
            logger.error("Failed to get text: " + selector, e);
            throw new RuntimeException("Get text failed: " + selector, e);
        }
    }



}

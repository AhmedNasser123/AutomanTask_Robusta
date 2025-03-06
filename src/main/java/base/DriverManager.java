package base;

import com.microsoft.playwright.*;
import utils.LoggerUtil;

import java.awt.*;

public class DriverManager {
    // Thread-local storage for Playwright components to ensure thread safety
    public static final ThreadLocal<Playwright> playwright  = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser  = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context  = new ThreadLocal<>();
    public static final ThreadLocal<Page> page  = new ThreadLocal<>();
    public static final ThreadLocal<APIRequestContext> requestContext = new ThreadLocal<>();
    // Logging and configuration utilities
   private static final LoggerUtil logger = new LoggerUtil(DriverManager.class);
    public static synchronized void setupBrowser() {
        try {
           playwright.set(Playwright.create());
            browser.set(playwright.get().chromium().launch(
                    new BrowserType.LaunchOptions().setChannel("chromium")
                            .setHeadless(false)
            ));
            context.set(browser.get().newContext(new Browser.NewContextOptions()
                    .setViewportSize(
                            Toolkit.getDefaultToolkit().getScreenSize().width,
                            Toolkit.getDefaultToolkit().getScreenSize().height
                    )
            ));

            page.set(context.get().newPage());


        } catch (Exception e) {
            throw new RuntimeException("Browser setup failed", e);
        }

    }

    public static void closeBrowser() {
        try {
            if (page.get() != null) {
                page.get().close();
                page.remove();
            }

            if (context.get() != null) {
                context.get().close();
                context.remove();
            }

            if (browser.get() != null) {
                browser.get().close();
                browser.remove();
            }

            if (playwright.get() != null) {
                playwright.get().close();
                playwright.remove();
            }

            // Force garbage collection
            System.gc();

        } catch (Exception e) {
            logger.error("Error during cleanup: " + e.getMessage());
        }
    }

}
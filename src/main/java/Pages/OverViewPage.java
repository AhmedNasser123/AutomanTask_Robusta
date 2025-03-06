package Pages;

import base.BasePage;
import com.microsoft.playwright.Page;

public class OverViewPage extends BasePage {

    public OverViewPage(Page page) {
        super(page);
    }

    private static final String completeText = ".complete-header";
    private static final String BTN_Finish = "#finish";

    public void FinishOrder() {
        click(BTN_Finish);

    }
    public boolean CompleteOrder() {
        return isDisplayed(completeText);
    }
}

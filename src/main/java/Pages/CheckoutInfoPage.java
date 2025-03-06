package Pages;

import base.BasePage;
import com.microsoft.playwright.Page;


import static Pages.YourCartPage.label_CheckOutInfo;

public class CheckoutInfoPage extends BasePage {

    public CheckoutInfoPage(Page page) {
        super(page);

    }
    private static final String TXT_FristName = "#first-name";
    private static final String TXT_LastName = "#last-name";
    private static final String TXT_ZipCode = "#postal-code";
    private static final String BTN_Continue = "#continue";

    public String InsertUserData(String FirstName,String LastName , String ZipCode){
        fill(TXT_FristName,FirstName);
        fill(TXT_LastName,LastName);
        fill(TXT_ZipCode,ZipCode);
        click(BTN_Continue);
       return getText(label_CheckOutInfo).trim() ;
    }

}

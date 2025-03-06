package Pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import utils.LoggerUtil;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class YourCartPage extends BasePage {


    public YourCartPage(Page page) {
        super(page);

    }
     private static final String label_inventory_item_name = "//div[@class='inventory_item_name']";
    private static final String label_Price_item_name =label_inventory_item_name+"/following::div[@class='inventory_item_price']";
    private static final String btn_CheckOut = "#checkout";
    public static final String label_CheckOutInfo = ".title";

    public List<String> getAllNamesAndPricesAddedProducts(){
        List<String> valueText = page.locator(label_inventory_item_name).allInnerTexts();
        List<String> valuePriceText = page.locator(label_Price_item_name).allInnerTexts();
        // loop by java Stream
        return IntStream.range(0, Math.min(valueText.size(), valuePriceText.size()))
                .mapToObj(i -> valueText.get(i) + " - " + valuePriceText.get(i))
                .collect(Collectors.toList());
    }

    public String Checkout(){
        click(btn_CheckOut);
        return getText(label_CheckOutInfo).trim();
    }

}
